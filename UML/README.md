# Sơ đồ UML - Hệ thống Quản lý Tù nhân Trại giam

## Tổng quan
Thư mục này chứa các sơ đồ UML cho hệ thống Quản lý Tù nhân Trại giam, được tạo bằng PlantUML để mô tả kiến trúc và thiết kế của hệ thống.

## Danh sách các sơ đồ

### 1. **UseCaseDiagram.puml** - Sơ đồ Use Case
**Mô tả:** Mô tả các chức năng chính của hệ thống và mối quan hệ với người dùng.
**Nội dung chính:**
- Các actor: Quản lý viên, Nhân viên trại giam
- Các use case chính: Quản lý tù nhân, Quản lý trại giam, Quản lý thăm nuôi
- Mối quan hệ include/extend giữa các use case

### 2. **ClassDiagram.puml** - Sơ đồ Class
**Mô tả:** Mô tả cấu trúc các lớp và mối quan hệ giữa chúng.
**Nội dung chính:**
- Entity classes: Person, Prisoner, Jail, Visit, User
- Controller classes: MainController, LoginController, PrisonerController, etc.
- View classes: LoginView, MainView, PrisonerView, etc.
- XML wrapper classes: PrisonerXML, JailXML, VisitXML
- Utility classes: FileUtils

### 3. **SequenceDiagram.puml** - Sơ đồ Sequence
**Mô tả:** Mô tả luồng tương tác giữa các đối tượng trong hệ thống.
**Nội dung chính:**
- Luồng đăng nhập hệ thống
- Luồng quản lý tù nhân (thêm, sửa, xóa, tìm kiếm)
- Tương tác giữa View, Controller, và Data layers

### 4. **ActivityDiagram.puml** - Sơ đồ Activity
**Mô tả:** Mô tả quy trình nghiệp vụ của hệ thống.
**Nội dung chính:**
- Quy trình đăng nhập và xác thực
- Quy trình quản lý tù nhân
- Quy trình quản lý trại giam
- Quy trình quản lý thăm nuôi

### 5. **StateDiagram.puml** - Sơ đồ State
**Mô tả:** Mô tả các trạng thái và chuyển đổi trạng thái của các đối tượng.
**Nội dung chính:**
- Trạng thái tù nhân: Đang giam giữ, Đã thả, Đã chuyển trại, Tử vong
- Trạng thái thăm nuôi: Đã lên lịch, Đang diễn ra, Hoàn thành, Hủy
- Trạng thái trại giam: Bình thường, Quá tải, Bảo trì, Đóng cửa

### 6. **ComponentDiagram.puml** - Sơ đồ Component
**Mô tả:** Mô tả kiến trúc hệ thống ở mức component.
**Nội dung chính:**
- Presentation Layer: Các view components
- Controller Layer: Các controller components
- Business Logic Layer: Các service components
- Data Access Layer: Các data access components
- Entity Layer: Các entity classes
- External Libraries: Java Swing, JAXB, JCalendar

### 7. **DeploymentDiagram.puml** - Sơ đồ Deployment
**Mô tả:** Mô tả cách triển khai hệ thống trên môi trường thực tế.
**Nội dung chính:**
- Client Machine: JAR file, JRE, Libraries
- File System: Data files, Configuration, Images
- Network: Backup storage, Logs
- System requirements và dependencies

## Cách sử dụng

### Yêu cầu
- PlantUML plugin hoặc online editor
- Java Runtime Environment

### Cách xem sơ đồ
1. **Online:** Sử dụng [PlantUML Online Server](http://www.plantuml.com/plantuml/uml/)
2. **IDE:** Cài đặt PlantUML plugin cho IDE (IntelliJ IDEA, VS Code, Eclipse)
3. **Command line:** Sử dụng PlantUML jar file

### Lệnh tạo hình ảnh
```bash
java -jar plantuml.jar *.puml
```

## Ý nghĩa trong báo cáo

### Chương 2: Phân tích và Thiết kế Hệ thống
- **UseCaseDiagram:** Mô tả yêu cầu chức năng
- **ClassDiagram:** Thiết kế cấu trúc dữ liệu
- **ComponentDiagram:** Kiến trúc hệ thống

### Chương 3: Thiết kế Hướng Đối tượng
- **ClassDiagram:** Áp dụng các nguyên lý OOP
- **StateDiagram:** Mô hình hóa trạng thái đối tượng

### Chương 4: Cài đặt và Triển khai
- **SequenceDiagram:** Luồng xử lý nghiệp vụ
- **ActivityDiagram:** Quy trình hệ thống
- **DeploymentDiagram:** Triển khai hệ thống

## Lưu ý
- Tất cả sơ đồ được tạo dựa trên code thực tế của project
- Có thể cần điều chỉnh khi có thay đổi trong code
- Sử dụng UTF-8 encoding để hiển thị tiếng Việt đúng
- Backup các file .puml trước khi chỉnh sửa

## Tác giả
Nhóm 4 - Quản lý Tù nhân Trại giam
- Nguyễn Tài Kiên (23010664)
- Nguyễn Thái Sơn (23010196)
- Nguyễn Ngô Tuấn Anh (23010826) 