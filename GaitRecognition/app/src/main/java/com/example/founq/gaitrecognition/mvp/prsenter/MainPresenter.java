package com.example.founq.gaitrecognition.mvp.prsenter;

import com.example.founq.gaitrecognition.MainActivity;
import com.example.founq.gaitrecognition.base.BasePresenter;
import com.example.founq.gaitrecognition.mvp.Contract;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainPresenter extends BasePresenter<MainActivity> implements Contract.Presenter {

    private BufferedWriter mWriter = null;
    private FileWriter mFileWriter = null;

    @Override
    public void pass() {
        view.get().show();
    }

    @Override
    public void getGaitInfo(boolean isRecord) {
        if (isRecord) {
            view.get().stopRecord();
            if (mWriter != null) {
                try {
                    mWriter.close();
                    mFileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            view.get().showGaitInfo();
        }
    }

    @Override
    public void writeToFile(File file, String content) {
        try {
            mFileWriter = new FileWriter(file, true);
            mWriter = new BufferedWriter(mFileWriter);
            mWriter.write(content);
            mWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void copyFile( File oldFile , File newFile){
        InputStream inputStream = null ;
        BufferedInputStream bufferedInputStream = null ;

        OutputStream outputStream = null ;
        BufferedOutputStream bufferedOutputStream = null ;

        try {
            inputStream = new FileInputStream( oldFile ) ;
            bufferedInputStream = new BufferedInputStream( inputStream ) ;

            outputStream = new FileOutputStream( newFile ) ;
            bufferedOutputStream = new BufferedOutputStream( outputStream ) ;

            byte[] b=new byte[1024];   //代表一次最多读取1KB的内容

            int length = 0 ; //代表实际读取的字节数
            while( (length = bufferedInputStream.read( b ) )!= -1 ){
                //length 代表实际读取的字节数
                bufferedOutputStream.write(b, 0, length );
            }
            //缓冲区的内容写入到文件
            bufferedOutputStream.flush();
            RecursionDeleteFile(oldFile);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }finally {

            if( bufferedOutputStream != null ){
                try {
                    bufferedOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if( bufferedInputStream != null){
                try {
                    bufferedInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    private void RecursionDeleteFile(File file) {
        if (file.isFile()) {
            file.delete();
            return;
        }
        if (file.isDirectory()) {
            File[] childFile = file.listFiles();
            if (childFile == null || childFile.length == 0) {
                file.delete();
                return;
            }
            for (File f : childFile) {
                RecursionDeleteFile(f);
            }
            file.delete();
        }
    }
}
