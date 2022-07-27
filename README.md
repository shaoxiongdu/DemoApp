# DemoApp
> [DemoApp飞书文档](https://li.feishu.cn/docx/doxcnYQ0XwLL2KAyy3LOcu0hd1c)
## 目录结构
```bash
. 
├── app                     // app练习
├── build.gradle  
├── gradle
├── gradle.properties
├── gradlew
├── gradlew.bat
├── local.properties
├── notice                  // 通知练习
├── permissionsystem        // 权限练习
├── README.md
└── settings.gradle

```
## 模块说明
### app
> activity练习
1. 有button,点击可跳转二级activity

2. 有button,点击可弹出Dialog

- a.Dialog内有edittext,可以输入文字，文字输入完成后会显示在activity内，且activity销毁后再次进入也要显示到activity内
- b.Dialog有确定和取消button

3. 在activity内画一个显示一个圆角矩形，和三角形，颜色为红色填充黄色边框

- 设置圆角矩形支持可点按，按下时为黄色填充、红色边框

4. 有图片列表显示，可滚动，可长按后角标显示选中状态
5. 监听主题改变后，将activity内字体改变颜色
   
### notice
> 通知练习
- 各种优先级通知
- 带大文本通知
- 点击跳转activity通知

### PermissionSystem
> 权限系统
- 网络权限
- 定位权限

...
