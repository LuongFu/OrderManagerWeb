<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Thanh Toán Ngân Hàng</title>
        <link rel="stylesheet" type="text/css" href="css/bank.css">
    </head>
    <body>
        <div class="container">
            <h2>Hướng Dẫn Thanh Toán Qua Ngân Hàng</h2>
            <p>Vui lòng làm theo các bước sau để thanh toán bằng mã QR qua ứng dụng ngân hàng của bạn.</p>

            <div class="payment-box banking-container">
                <div class="qr-container">
                    <img src="images/bank_qr.png" alt="Mã QR Ngân hàng">
                </div>
                <ol>
                    <li>Mở ứng dụng ngân hàng trên điện thoại của bạn.</li>
                    <li>Đăng nhập vào tài khoản ngân hàng của bạn.</li>
                    <li>Truy cập vào mục "Thanh toán" hoặc "Quét mã QR".</li>
                    <li>Chọn chức năng quét mã QR và hướng camera vào mã QR trên màn hình.</li>
                    <li>Xác nhận thông tin thanh toán hiển thị trên ứng dụng.</li>
                    <li>Nhập số tiền cần thanh toán (nếu chưa có sẵn).</li>
                    <li>Xác nhận giao dịch bằng mật khẩu hoặc phương thức bảo mật của bạn.</li>
                    <li>Nhận thông báo giao dịch thành công từ ngân hàng.</li>
                </ol>
            </div>

            <p class="back-link"><a href="support.jsp">Quay lại</a></p>
        </div>
    </body>
</html>
