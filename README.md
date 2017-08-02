[![](https://jitpack.io/v/chuross/extra-textview.svg)](https://jitpack.io/#chuross/extra-textview)

---

# ExtraTextView
additional functions for TextView.

<img width="350" alt="2017-04-20 15 17 48" src="https://cloud.githubusercontent.com/assets/1422031/25216241/a30182fc-25dc-11e7-8124-a313ff92b5a3.png">

## Usage
see [sample](https://github.com/chuross/expandable-layout/tree/master/sample)

### In your layout
```
<com.github.chuross.widget.ExtraTextView
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:ext_txt_drawable="@drawable/your_icon"
    app:ext_txt_drawableWidth="24dp"
    app:ext_txt_drawableHeight="24dp"/>
```

## XMLAttributes
| name | type | description | etc |
| --- | --- | --- | --- |
| ext_txt_drawable | resource | drawable icon resource | |
| ext_txt_drawableTint | color | drawable tint color | |
| ext_txt_drawableWidth | dimen | drawable width | If not defined, width will be `match_parent`.|
| ext_txt_drawableHeight | dimen | drawable height | If not defined, height will be the same as `view.width`.|
| ext_txt_drawablePosition| enum | drawable position | `top` or `bottom` or `left` or `right` |
| ext_txt_drawableFit| boolean | auto fit drawable padding. If you use `match_parent`. | drawable position is `left` or `right`. |
| ext_txt_cornerBorderColor | color | border color | required `ext_txt_cornerBorderSize` and `ext_txt_cornerRadius` |
| ext_txt_cornerBorderSize | dimen | border width | required `ext_txt_cornerRadius` |
| ext_txt_cornerRadius | dimen | corner radius | required `ext_txt_cornerBorderSize` |

## Download
### Gradle
1. add JitPack repository to your project root `build.gradle`.
```
repositories {
    maven { url "https://jitpack.io" }
}
```

2. add the dependency

latest version:
[![](https://jitpack.io/v/chuross/extra-textview.svg)](https://jitpack.io/#chuross/extra-textview)

```
dependencies {
    compile 'com.github.chuross.extra-textview:x.x.x'
}
```
