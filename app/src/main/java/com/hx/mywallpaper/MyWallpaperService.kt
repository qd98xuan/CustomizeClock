package com.hx.mywallpaper

import android.service.wallpaper.WallpaperService
import android.view.SurfaceHolder

/**
* @author huangxuan
* @since 2022/9/6 09:31
* QQ: 1360643904
* 自定义壁纸引擎
*/
class MyWallpaperService:WallpaperService() {
    inner class WallpaperEngine:WallpaperService.Engine() {
        override fun getSurfaceHolder(): SurfaceHolder {
            return super.getSurfaceHolder()
        }

        override fun onCreate(surfaceHolder: SurfaceHolder?) {
            super.onCreate(surfaceHolder)

        }
    }
    override fun onCreateEngine(): Engine = WallpaperEngine()
}