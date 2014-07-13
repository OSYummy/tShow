package com.wisedu.tShow.tools.wechat.types;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-7-13
 * Time: 下午6:16
 * To change this template use File | Settings | File Templates.
 */
public enum UploadMediaFileType {
    // 图片: 128K，支持JPG格式
    image,

    // 语音：256K，播放长度不超过60s，支持AMR\MP3格式
    voice,

    // 视频：1MB，支持MP4格式
    video,

    // thumb：64KB，支持JPG格式
    thumb,

    // 图文消息
    news
}
