package com.minld._spring_boot.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.minld._spring_boot.exception.AppException;
import com.minld._spring_boot.exception.ErrorCode;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmailVerify(String email, String activationCode) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom("luanek1702@gmail.com");
            helper.setTo(email);
            helper.setSubject("Kích hoạt tài khoản");

            String htmlBody = generateHtmlBody(email, activationCode);
            helper.setText(htmlBody, true);

            javaMailSender.send(message);
        } catch (MessagingException e) {
            log.error("Failed to send email: {}", e.getMessage());
            throw new AppException(ErrorCode.EMAIL_SENDING_FAILED);
        }
    }

    private String generateHtmlBody(String email, String activationCode) {

        activationCode = StringUtils.hasText(activationCode) ? activationCode : "N/A";
        email = StringUtils.hasText(email) ? email : "N/A";

        String htmlTemplate =
                """
				<html lang='vi'>
				<head>
					<meta charset='UTF-8' />
					<meta name='viewport' content='width=device-width, initial-scale=1.0' />
					<title>Kích hoạt tài khoản - @The Craft LD</title>
				</head>
				<body style="margin: 0; padding: 0; background-color: #f3f4f6; font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif;">
					<table role='presentation' cellspacing='0' cellpadding='0' width='100%%' bgcolor='#f3f4f6'>
					<tr>
						<td align='center' style='padding: 40px 20px;'>
						<table role='presentation' cellspacing='0' cellpadding='0' width='100%%' style='max-width: 600px; background: #ffffff; border-radius: 12px; box-shadow: 0 10px 25px rgba(0,0,0,0.08); overflow: hidden;'>
							<!-- Header -->
							<tr>
							<td style='background-color: #1e40af; padding: 32px 24px; text-align: center;'>
								<h1 style='color: #ffffff; font-size: 28px; margin: 0;'>🎉 Chào mừng bạn đến với The Craft LD!</h1>
							</td>
							</tr>
							<!-- Nội dung -->
							<tr>
							<td style='padding: 32px;'>
								<p style='font-size: 16px; color: #374151; margin-top: 0;'>Xin chào <strong>%s</strong>,</p>
								<p style='font-size: 16px; color: #374151;'>Cảm ơn bạn đã đăng ký tài khoản tại <strong>The Craft LD</strong>. Để hoàn tất quá trình đăng ký, vui lòng sử dụng mã kích hoạt bên dưới:</p>
								<div style='margin: 24px 0; text-align: center;'>
								<span style='display: inline-block; background-color: #e0f2fe; color: #0284c7; font-size: 22px; font-weight: 700; padding: 14px 28px; border-radius: 8px;'>%s</span>
								</div>
								<p style='font-size: 15px; color: #6b7280;'>Vui lòng nhập mã kích hoạt này trong vòng <strong>5 phút</strong> để đảm bảo tính bảo mật.</p>
								<p style='font-size: 15px; color: #6b7280;'>Nếu bạn không đăng ký tài khoản, vui lòng bỏ qua email này. Không ai có thể truy cập tài khoản nếu không có mã kích hoạt.</p>
							</td>
							</tr>
							<!-- Footer -->
							<tr>
							<td style='background-color: #f9fafb; padding: 24px; text-align: center;'>
								<p style='font-size: 14px; color: #9ca3af;'>Cần trợ giúp? Liên hệ <a href='https://youtube.com/@hoidanit' style='color: #3b82f6; text-decoration: none;'>The Craft LD</a></p>
								<p style='font-size: 13px; color: #d1d5db;'>© 2025 The Craft LD. All rights reserved.</p>
							</td>
							</tr>
						</table>
						</td>
					</tr>
					</table>
				</body>
				</html>
				""";

        // Thoát các ký tự % bằng %% trong chuỗi HTML, chỉ giữ %s cho placeholder
        return String.format(htmlTemplate, email, activationCode);
    }
}
