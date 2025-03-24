<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Item Manager</title>
        <!--<script src="script.js"></script>-->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    </head>
    <h2>Hello, admin ${sessionScope.session_login}</h2>
    <a href="logout" class="nav-link logout-link">Sign out</a>
    <body class="container mt-4">
        <h2 class="text-center">Item Manager</h2>

        <!-- Tìm kiếm -->
        <div class="form-inline mb-3">
            <input type="text" id="search" class="form-control mr-2" placeholder="Tìm kiếm theo tên món" />

            <label for="sortBy" class="mr-2">Sắp xếp theo:</label>
            <select id="sortBy" class="form-control mr-2">
                <option value="nameItem">Tên món</option>
                <option value="price">Giá (VND)</option>
                <option value="createBy">Tạo bởi</option>
            </select>

            <label for="sortOrder" class="mr-2">Kiểu sắp xếp:</label>
            <select id="sortOrder" class="form-control mr-2">
                <option value="asc">Tăng dần</option>
                <option value="desc">Giảm dần</option>
            </select>

            <button type="button" class="btn btn-primary" onclick="sortTable()">Sắp xếp</button>
            <a href="addItem.jsp" class="btn btn-success mb-3">Thêm món mới</a>
            <a href="staticstic.jsp" class="btn btn-success mb-3">Xem thống kê</a>
        </div>

        <h3 class="mt-4 text-center">Item List</h3>

        <!-- Pagination Controls -->
        <div id="pagination" class="mt-3 text-center">
            <button class="btn btn-secondary" id="prevBtn" onclick="changePage(-1)">Previous</button>
            <span id="pageNum">Page 1</span>
            <button class="btn btn-secondary" id="nextBtn" onclick="changePage(1)">Next</button>
            <div id="pageNumbers" class="mt-2">
                <!-- Pagination numbers will be dynamically inserted here -->
            </div>
        </div>

        <div class="table-responsive">
            <table class="table table-bordered table-hover" id="itemTable">
                <thead class="table-dark">
                    <tr>
                        <th>ID</th>
                        <th>Hình ảnh</th>
                        <th>Tên món</th>
                        <th>Giá</th>
                        <th>Mô tả</th>
                        <th>Tạo bởi</th>
                        <th>Xử lí</th>
                    </tr>
                </thead>
                <tbody id="bookTable">
                    <!-- Iterate over the ListItem stored in session -->
                    <c:forEach var="item" items="${requestScope.ListItem}">
                        <tr>
                            <td>${item.itemId}</td>
                            <td><img class="card-img-top" width="auto" height="100px" src="food/${item.image}"
                                     alt="Card image cap"></td>
                            <td>${item.nameItem}</td>
                            <td>${item.price}</td>
                            <td>${item.description}</td>
                            <td>${item.createBy}</td>
                            <td>
                                <a href="item-manager?action=UPDATE&itemId=${item.itemId}" class="btn btn-warning btn-sm">Cập nhật</a> |
                                <a href="item-manager?action=DELETE&itemId=${item.itemId}" onclick="countDelete(); return confirm('Bạn có chắc bạn muốn xóa món này khỏi danh sách không?')" class="btn btn-danger btn-sm">Xóa</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
        <script>
                                    function countDelete() {
                                        let deleteCount = localStorage.getItem("deleteItemCount") || 0;
                                        deleteCount++;
                                        localStorage.setItem("deleteItemCount", deleteCount);
                                    }

                                    let currentPage = 1;
                                    const rowsPerPage = 10;
                                    const rows = document.querySelectorAll("#bookTable tr");
                                    const totalRows = rows.length;
                                    const totalPages = Math.ceil(totalRows / rowsPerPage);

                                    // Display rows for the current page
                                    function displayRows() {
                                        const start = (currentPage - 1) * rowsPerPage;
                                        const end = currentPage * rowsPerPage;

                                        rows.forEach((row, index) => {
                                            if (index >= start && index < end) {
                                                row.style.display = '';
                                            } else {
                                                row.style.display = 'none';
                                            }
                                        });

                                        document.getElementById("pageNum").textContent = `Page ${currentPage}`;
                                        document.getElementById("prevBtn").disabled = currentPage === 1;
                                        document.getElementById("nextBtn").disabled = currentPage === totalPages;

                                        // Update pagination numbers
                                        updatePaginationNumbers();
                                    }

                                    // Change page number
                                    function changePage(direction) {
                                        currentPage += direction;
                                        displayRows();
                                    }

                                    // Update page numbers
                                    function updatePaginationNumbers() {
                                        const pageNumbersContainer = document.getElementById("pageNumbers");
                                        pageNumbersContainer.innerHTML = ''; // Clear existing page numbers

                                        for (let i = 1; i <= totalPages; i++) {
                                            const pageNumButton = document.createElement("button");
                                            pageNumButton.textContent = i;
                                            pageNumButton.classList.add("btn", "btn-secondary", "mx-1");
                                            pageNumButton.onclick = () => {
                                                currentPage = i;
                                                displayRows();
                                            };
                                            if (i === currentPage)
                                                pageNumButton.disabled = true;
                                            pageNumbersContainer.appendChild(pageNumButton);
                                        }
                                    }

                                    // Initial display of rows
                                    displayRows();

                                    // Search functionality
                                    document.getElementById('search').addEventListener('keyup', function () {
                                        const searchValue = this.value.toLowerCase();
                                        let filteredRows = Array.from(rows).filter(row => {
                                            const textContent = row.textContent.toLowerCase();
                                            return textContent.includes(searchValue);
                                        });

                                        filteredRows.forEach(row => row.style.display = '');
                                        rows.forEach(row => {
                                            if (!filteredRows.includes(row))
                                                row.style.display = 'none';
                                        });

                                        // Update pagination
                                        const filteredTotalPages = Math.ceil(filteredRows.length / rowsPerPage);
                                        currentPage = 1;
                                        document.getElementById("pageNum").textContent = `Page 1`;
                                        document.getElementById("prevBtn").disabled = true;
                                        document.getElementById("nextBtn").disabled = filteredTotalPages <= 1;

                                        displayRows();
                                    });

                                    // Sorting functionality
                                    function sortTable() {
                                        let sortBy = document.getElementById("sortBy").value;
                                        let sortOrder = document.getElementById("sortOrder").value;

                                        let sortedRows = Array.from(rows).sort((a, b) => {
                                            const cellA = a.cells[getColumnIndex(sortBy)].textContent.trim();
                                            const cellB = b.cells[getColumnIndex(sortBy)].textContent.trim();

                                            if (sortBy === 'price') {
                                                return (parseFloat(cellA) - parseFloat(cellB)) * (sortOrder === 'asc' ? 1 : -1);
                                            }
                                            return cellA.localeCompare(cellB) * (sortOrder === 'asc' ? 1 : -1);
                                        });

                                        rows.forEach(row => row.style.display = 'none');
                                        sortedRows.forEach(row => row.style.display = '');

                                        displayRows();
                                    }

                                    // Get column index based on sorting attribute
                                    function getColumnIndex(sortBy) {
                                        switch (sortBy) {
                                            case 'nameItem':
                                                return 1;
                                            case 'price':
                                                return 2;
                                            case 'createBy':
                                                return 4;
                                            default:
                                                return 0;
                                        }
                                    }
        </script>
    </body>
</html>
