<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Trung Tâm Hỗ Trợ</title>
        <link rel="stylesheet" href="css/support_style.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    </head>
    <body>
        <!-- Navigation bar -->
        <nav class="navbar navbar-expand-lg navbar-dark">   
            <div class="container-fluid">
                <a class="navbar-brand me-auto d-flex align-items-center" href="#">
                    <img src="images/logo.png" alt="FapFood Logo" style="height: 60px;">
                    <img src="images/logo2.png" alt="FapFood Second Logo" style="height: 70px; margin-left: 15px;">
                </a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse justify-content-center" id="navbarNav">
                    <ul class="navbar-nav gap-4">
                        <li class="nav-item"><a class="nav-link" href="#">Home<span class="underline"></span></a></li>
                        <li class="nav-item"><a class="nav-link" href="#">About<span class="underline"></span></a></li>
                        <li class="nav-item"><a class="nav-link" href="index.jsp">Menu<span class="underline"></span></a></li>
                        <li class="nav-item"><a class="nav-link" href="#">Order<span class="underline"></span></a></li>
                        <li class="nav-item"><a class="nav-link" href="#">Support<span class="underline"></span></a></li>
                    </ul>
                </div>
                <div class="d-flex ms-auto">
                    <a href="#" class="btn btn-outline-light me-2">Login</a>
                    <a href="#" class="btn btn-signup">Sign Up</a>
                </div>
            </div>
        </nav>

        <div class="container mt-4">
            <h1 class="text-center">Trung Tâm Hỗ Trợ</h1>
            <p class="text-center">
                Trung tâm hỗ trợ khách hàng của FapFood sẽ hỗ trợ các vấn đề liên quan đến mua Phiếu trực tuyến và thanh toán tại website FapFood.com. <br>
                Các vấn đề khác chúng tôi sẽ không tiếp nhận, vui lòng xem thông tin trên website hoặc liên hệ trực tiếp với căn tin để được giải quyết.
            </p>

            <div class="info-boxes">
                <div class="info-box">Zalo: <a href="https://id.zalo.me/account?continue=https%3A%2F%2Fchat.zalo.me%2F">zalo.me/fapfood</a></div>
                <div class="info-box">Hotline: 024 7308 8890</div>
                <div class="info-box">Email: <a href="mailto:hotro@fapfood.vn">hotro@fapfood.vn</a></div>
                <div class="info-box">Hướng dẫn sử dụng: <a href="step-support.jsp">kb.fapfood.com</a></div>
            </div>
        </div>

        <!-- Phần nội dung chính -->
        <div class="container content-section">
            <div class="faq">
                <h2 class="section-title">Câu hỏi thường gặp</h2>
                <div class="faq-item">
                    <h3>Tôi có thể đặt đồ ăn từ những căn tin nào trên FapFood?</h3>
                    <p>Hiện tại, bạn có thể đặt đồ ăn từ các căn tin sau:</p>
                    <ul>
                        <li>Căn tin Đại học Bách Khoa</li>
                        <li>Căn tin Đại học Kinh tế</li>
                        <li>Căn tin Đại học Sư phạm</li>
                        <li>Căn tin Đại học Công nghệ Thông tin</li>
                        <li>Căn tin Đại học Khoa học Tự nhiên</li>
                        <li>Căn tin Đại học Quốc gia</li>
                    </ul>
                    <p>Chúng tôi sẽ tiếp tục mở rộng hệ thống trong thời gian tới.</p>
                </div>
                <div class="faq-item">
                    <h3>Tôi có thể đặt trước suất ăn không?</h3>
                    <p>Có, bạn có thể đặt trước suất ăn và chọn thời gian nhận hàng theo lịch mở bán của căn tin.</p>
                </div>
                <div class="faq-item">
                    <h3>Sau khi thanh toán tôi nhận đồ ăn như thế nào?</h3>
                    <p>Sau khi thanh toán thành công, bạn sẽ nhận được mã đơn hàng qua email hoặc tin nhắn. Vui lòng xuất trình mã này tại quầy để nhận đồ ăn.</p>
                </div>
                <div class="faq-item">
                    <h3>Tôi có thể hủy hoặc đổi món sau khi đặt không?</h3>
                    <p>Rất tiếc, đơn hàng sau khi xác nhận thanh toán không thể hủy hoặc đổi món. Vui lòng kiểm tra kỹ trước khi đặt hàng.</p>
                </div>
                <div class="faq-item">
                    <h3>FapFood có hỗ trợ giao hàng tận nơi không?</h3>
                    <p>Hiện tại, FapFood chỉ hỗ trợ đặt trước và nhận tại căn tin. Chúng tôi sẽ sớm triển khai dịch vụ giao hàng tận nơi.</p>
                </div>
            </div>

            <div class="guidelines">
                <h2 class="section-title">Hướng dẫn</h2>
                <ul>
                    <li><a href="step-support.jsp">Hướng dẫn các bước đặt món trên FapFood</a></li>
                    <li><a href="page-bank.jsp">Cách thanh toán bằng thẻ ngân hàng</a></li>
                    <li><a href="page-momo.jsp">Cách thanh toán bằng ví điện tử MoMo</a></li>
                </ul>
            </div>

        </div>

        <!-- Footer tách biệt -->
        <footer class="footer">
            <div class="container">
                <div class="footer-content">
                    <div class="company-info" style="flex: 6;">
                        <p><strong>CÔNG TY TNHH VPHK</strong></p>
                        <p><strong>CHI NHÁNH ĐÀ NẴNG</strong></p>
                        <p>Số ĐKKD: 0315367026-001 - Nơi cấp: Sở kế hoạch và đầu tư Tp. Đà Nẵng</p>
                        <p>Đăng ký lần đầu ngày 15/03/2022</p>
                        <p>Địa chỉ: 123 Nguyễn Văn Linh, P. Hải Châu 1, Q. Hải Châu, Tp. Đà Nẵng</p>

                    </div>
                    <div class="partners" style="flex: 4;">
                        <h4>ĐỐI TÁC</h4>
                        <div class="partner-logos" style="display: grid; grid-template-columns: repeat(5, 1fr); gap: 10px;">
                            <img src="images/logo_0_0.png" alt="Beta Cinemas">
                            <img src="images/logo_0_1.png" alt="Mega GS">
                            <img src="images/logo_0_2.png" alt="CineStar">
                            <img src="images/logo_1_0.png" alt="Dcine">
                            <img src="images/logo_1_1.png" alt="Cinemax">
                            <img src="images/logo_1_2.png" alt="Starlight">
                            <img src="images/logo_2_0.png" alt="Rio Cinemas">
                            <img src="images/logo_2_1.png" alt="Touch Cinema">
                            <img src="images/logo_2_2.png" alt="Momo">
                            <img src="images/logo.png" alt="Another Brand">
                        </div>
                    </div>
                    <div class="certification" style="flex: 2; display: flex; align-items: center; justify-content: center;">
                        <img src="images/bo-cong-thuong.png" alt="Bộ Công Thương">
                    </div>
                </div>
            </div>
        </footer>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
