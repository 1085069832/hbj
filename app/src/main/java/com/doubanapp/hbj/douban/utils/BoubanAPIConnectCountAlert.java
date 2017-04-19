package com.doubanapp.hbj.douban.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * 豆瓣接口请求次数提示
 * Created by Administrator on 2017/3/24 0024.
 */
public class BoubanAPIConnectCountAlert {
    private long lastTimes;
    private int count = 0;

    public BoubanAPIConnectCountAlert(Context mContext) {
        try {
            File file;
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)
                    || !Environment.isExternalStorageRemovable()) {
                file = new File(Environment.getExternalStorageDirectory(), "boubanapicount");
            } else {
                file = new File(MyUtils.getContext().getCacheDir(), "boubanapicount");
            }
            if (!file.exists()) {
                //文件不存在
                //把时间和count写入文件
                count++;
                writeToFile(file);
            } else {
                //已经存在
                //读取
                long currentTimes = System.currentTimeMillis();
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                StringBuffer sb = new StringBuffer();
                String len;
                while ((len = br.readLine()) != null) {
                    if (sb.length() == 0) {
                        sb.append(len).append(" ");
                    } else {
                        sb.append(len);
                    }
                }
                MyLogUtils.i("BoubanAPIConnectCountAlert", sb.toString());
                //读取到时间和count
                String[] split = sb.toString().split(" ");
                lastTimes = Long.parseLong(split[0]);
                count = Integer.parseInt(split[1]);
                MyLogUtils.i("BoubanAPIConnectCountAlert", "转换后" + lastTimes + " " + count);
                MyLogUtils.i("BoubanAPIConnectCountAlert", "差值" + (currentTimes - lastTimes));
                if (currentTimes - lastTimes <= 3600000) {
                    //在一小时内
                    if (count >= 140) {
                        MyLogUtils.i("BoubanAPIConnectCountAlert", "currentTimes - lastTimes <= 60000 , count >= 1");
                        new SweetAlertDialog(mContext, SweetAlertDialog.WARNING_TYPE)
                                .setTitleText("先歇歇吧!")
                                .setContentText("请求豆瓣数据的频率超过了" + count + "次/小时(上限为150次每小时)")
                                .setConfirmText("确定")
                                .show();
                    }
                    count++;
                    writeToFile(file);
                } else {
                    //两次请求超过了一小时
                    count = 1;
                    writeToFile(file);
                }
                br.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeToFile(File file) throws IOException {
        lastTimes = System.currentTimeMillis();
        if (file.exists()) {
            file.delete();
            file.createNewFile();
        }
        FileWriter fw = new FileWriter(file);
        fw.write(lastTimes + "\n" + count);
        fw.flush();
        fw.close();
    }
}
