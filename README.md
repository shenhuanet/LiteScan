# 轻扫 (LiteScan)

一个简洁、高效的 Android 二维码扫描应用，基于 Jetpack Compose 和 ML Kit 构建。

## 功能特性

- 📷 **实时扫描**：使用相机实时扫描二维码和条形码
- 🖼️ **相册识别**：从相册选择图片进行二维码识别
- 🔗 **智能识别**：自动识别 URL 链接并提供快速打开功能
- 📋 **结果复制**：扫描结果可选中复制
- 🎨 **现代化 UI**：采用 Material Design 3 设计规范
- 🚀 **流畅体验**：基于 Jetpack Compose 构建的原生 UI

## 技术栈

- **开发语言**：Kotlin
- **UI 框架**：Jetpack Compose
- **相机功能**：CameraX
- **二维码识别**：Google ML Kit Barcode Scanning
- **导航**：Navigation Compose
- **权限管理**：Accompanist Permissions
- **最低 SDK**：Android 10 (API 29)
- **目标 SDK**：Android 14 (API 36)

## 项目结构

```
app/src/main/java/com/shenhua/litescan/
├── MainActivity.kt              # 主活动，处理权限和导航
├── QrCodeScannerScreen.kt       # 扫描界面，支持相机和相册
├── ResultScreen.kt              # 结果展示界面
├── PermissionRequestScreen.kt   # 权限请求界面
└── ui/theme/                    # 主题配置
    ├── Color.kt
    ├── Theme.kt
    └── Type.kt
```

## 开始使用

### 环境要求

- Android Studio Hedgehog (2023.1.1) 或更高版本
- JDK 11 或更高版本
- Android SDK 36
- Gradle 8.x

### 构建步骤

1. 克隆项目
```bash
git clone <repository-url>
cd litescan
```

2. 打开项目
```bash
# 使用 Android Studio 打开项目
# 或使用命令行构建
./gradlew build
```

3. 运行应用
```bash
./gradlew installDebug
```

## 核心依赖

```kotlin
// Jetpack Compose
implementation("androidx.compose.ui:ui")
implementation("androidx.compose.material3:material3")
implementation("androidx.activity:activity-compose")

// CameraX
implementation("androidx.camera:camera-core")
implementation("androidx.camera:camera-camera2")
implementation("androidx.camera:camera-lifecycle")
implementation("androidx.camera:camera-view")

// ML Kit Barcode Scanning
implementation("com.google.mlkit:barcode-scanning")

// Navigation
implementation("androidx.navigation:navigation-compose")

// Permissions
implementation("com.google.accompanist:accompanist-permissions")
```

## 使用说明

1. **首次启动**：应用会请求相机权限
2. **扫描二维码**：将相机对准二维码，应用会自动识别
3. **从相册选择**：点击右下角的 ➕ 按钮，从相册选择包含二维码的图片
4. **查看结果**：扫描成功后会跳转到结果页面
5. **打开链接**：如果扫描结果是 URL，可以直接点击"打开链接"按钮
6. **继续扫描**：点击"返回并重新扫描"按钮继续扫描

## 支持的格式

应用支持所有常见的条码格式：

- QR Code（二维码）
- Code 128
- Code 39
- Code 93
- EAN-8
- EAN-13
- UPC-A
- UPC-E
- Data Matrix
- PDF417
- Aztec
- 等更多格式

## 权限说明

应用需要以下权限：

- **相机权限** (`CAMERA`)：用于实时扫描二维码
- **读取存储权限**：通过照片选择器访问相册（Android 13+ 无需显式权限）

## 开发计划

- [ ] 添加扫描历史记录
- [ ] 添加批量扫描功能
- [ ] 支持更多扫描结果操作（分享、保存等）
- [ ] 支持自定义扫描框样式

## 贡献指南

欢迎提交 Issue 和 Pull Request！

1. Fork 本项目
2. 创建特性分支 (`git checkout -b feature/AmazingFeature`)
3. 提交更改 (`git commit -m 'Add some AmazingFeature'`)
4. 推送到分支 (`git push origin feature/AmazingFeature`)
5. 开启 Pull Request

## 许可证

本项目采用 MIT 许可证 - 详见 [LICENSE](LICENSE) 文件

## 联系方式

- 作者：shenhua
- 邮箱：shenhuanet@126.com

## 致谢

- [Google ML Kit](https://developers.google.com/ml-kit) - 提供强大的条码识别能力
- [CameraX](https://developer.android.com/training/camerax) - 简化相机开发
- [Jetpack Compose](https://developer.android.com/jetpack/compose) - 现代化的 UI 工具包
