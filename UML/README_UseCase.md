# Sơ đồ Use Case - Hệ thống Quản lý Tù nhân Trại giam

## Tổng quan
Thư mục này chứa các sơ đồ Use Case được tách riêng theo từng chức năng của hệ thống Quản lý Tù nhân Trại giam, giúp dễ hiểu và quản lý hơn.

## Danh sách các sơ đồ Use Case

### 1. **UseCase_Overview.puml** - Sơ đồ Use Case Tổng quan
**Mô tả:** Sơ đồ tổng quan toàn bộ hệ thống với các package chức năng chính.
**Nội dung chính:**
- 6 package chức năng chính
- 2 actor: Quản lý viên và Nhân viên trại giam
- Mối quan hệ giữa các chức năng
- Phân quyền rõ ràng

### 2. **UseCase_Authentication.puml** - Xác thực và Đăng nhập
**Mô tả:** Chức năng xác thực và quản lý phiên đăng nhập.
**Các use case:**
- Đăng nhập hệ thống
- Xác thực thông tin đăng nhập
- Kiểm tra quyền truy cập
- Đăng xuất
- Thay đổi mật khẩu
- Khóa tài khoản

### 3. **UseCase_PrisonerManagement.puml** - Quản lý Tù nhân
**Mô tả:** Chức năng quản lý thông tin tù nhân.
**Các use case:**
- Thêm tù nhân mới
- Cập nhật thông tin tù nhân
- Xóa tù nhân
- Tìm kiếm tù nhân
- Xem danh sách tù nhân
- Upload ảnh chân dung
- Cập nhật trạng thái tù nhân
- Kiểm tra thời hạn giam giữ

### 4. **UseCase_JailManagement.puml** - Quản lý Trại giam
**Mô tả:** Chức năng quản lý thông tin trại giam.
**Các use case:**
- Thêm trại giam mới
- Cập nhật thông tin trại giam
- Xóa trại giam
- Xem danh sách trại giam
- Kiểm tra tình trạng quá tải
- Cập nhật sức chứa trại giam
- Cảnh báo quá tải

### 5. **UseCase_VisitManagement.puml** - Quản lý Thăm nuôi
**Mô tả:** Chức năng quản lý hoạt động thăm nuôi.
**Các use case:**
- Đăng ký lịch thăm nuôi
- Cập nhật lịch thăm nuôi
- Hủy lịch thăm nuôi
- Quản lý thông tin người thăm
- Kiểm tra lịch thăm nuôi
- Xác nhận/từ chối thăm nuôi
- Cập nhật trạng thái thăm nuôi

### 6. **UseCase_Reporting.puml** - Báo cáo và Thống kê
**Mô tả:** Chức năng báo cáo và thống kê hệ thống.
**Các use case:**
- Xem thống kê tổng quan
- Xuất báo cáo tù nhân
- Xuất báo cáo thăm nuôi
- Xem biểu đồ thống kê
- Thống kê theo trại giam
- Thống kê theo thời gian
- Báo cáo tình trạng quá tải

### 7. **UseCase_SystemManagement.puml** - Quản lý Hệ thống
**Mô tả:** Chức năng quản lý và bảo trì hệ thống.
**Các use case:**
- Sao lưu dữ liệu
- Khôi phục dữ liệu
- Cập nhật hệ thống
- Quản lý người dùng
- Cấu hình hệ thống
- Xem nhật ký hệ thống
- Kiểm tra hiệu suất

## Phân quyền người dùng

### Quản lý viên (Admin)
- **Toàn quyền:** Tất cả các chức năng
- **Quản lý người dùng:** Thêm, sửa, xóa người dùng
- **Cấu hình hệ thống:** Thay đổi cài đặt hệ thống
- **Xuất báo cáo:** Tạo và xuất báo cáo

### Nhân viên trại giam (Staff)
- **Quyền hạn chế:** Chỉ xem và cập nhật dữ liệu
- **Không thể:** Xóa dữ liệu quan trọng
- **Không thể:** Cấu hình hệ thống
- **Không thể:** Quản lý người dùng

## Mối quan hệ giữa các Use Case

### Include Relationships (<<include>>)
- **Thêm/Sửa tù nhân** → **Upload ảnh chân dung**
- **Quản lý trại giam** → **Kiểm tra tình trạng quá tải**
- **Đăng ký thăm nuôi** → **Quản lý thông tin người thăm**
- **Báo cáo** → **Thống kê tổng quan**

### Extend Relationships (<<extend>>)
- **Tìm kiếm tù nhân** → **Xem chi tiết tù nhân**
- **Xem danh sách trại giam** → **Xem thống kê sức chứa**
- **Kiểm tra lịch thăm nuôi** → **Xác nhận/Từ chối thăm nuôi**

## Cách sử dụng trong báo cáo

### Chương 2: Phân tích và Thiết kế Hệ thống
1. **UseCase_Overview.puml:** Giới thiệu tổng quan hệ thống
2. **UseCase_Authentication.puml:** Yêu cầu bảo mật
3. **UseCase_PrisonerManagement.puml:** Yêu cầu chức năng chính
4. **UseCase_JailManagement.puml:** Yêu cầu quản lý trại giam
5. **UseCase_VisitManagement.puml:** Yêu cầu quản lý thăm nuôi
6. **UseCase_Reporting.puml:** Yêu cầu báo cáo
7. **UseCase_SystemManagement.puml:** Yêu cầu quản lý hệ thống

### Trình bày trong báo cáo
- Mỗi sơ đồ nên có mô tả ngắn gọn
- Giải thích các actor và use case chính
- Phân tích mối quan hệ include/extend
- So sánh quyền hạn giữa Admin và Staff

## Lưu ý khi sử dụng
- Tất cả sơ đồ đều sử dụng tiếng Việt
- Có thể điều chỉnh theo yêu cầu cụ thể của giảng viên
- Backup file gốc trước khi chỉnh sửa
- Kiểm tra tính nhất quán giữa các sơ đồ

## Tác giả
Nhóm 4 - Quản lý Tù nhân Trại giam
- Nguyễn Tài Kiên (23010664)
- Nguyễn Thái Sơn (23010196)
- Nguyễn Ngô Tuấn Anh (23010826) 