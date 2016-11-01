package com.example.ansan.myapplication;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }




    public void onclick (View v){

        if(isStoragePermissionGranted()== false) {
            Toast.makeText(getApplication(),"SD카드사용 에러",Toast.LENGTH_SHORT).show();
            return;
        }
        String strpath = Environment.getExternalStorageDirectory().getAbsolutePath();
    String folder = strpath +"/dabin";
        String filename = folder + "/test.txt";
        File mydir = new File(folder);

        switch (v.getId()){
            case R.id.button4:

                mydir.mkdir();
                Toast.makeText(getApplication(),"폴더생성완료",Toast.LENGTH_SHORT).show();
                break;
            case R.id.button5:

                mydir.delete();
                Toast.makeText(getApplication(),"폴더삭제완료",Toast.LENGTH_SHORT).show();
                break;

            case R.id.button6:

                try {
                    FileOutputStream fos = new FileOutputStream(filename);
                    fos.write("안녕하세요".getBytes());
                    fos.close();

                    Toast.makeText(getApplication(),"파일생성성공",Toast.LENGTH_SHORT).show();
                } catch (FileNotFoundException e) {
                    Toast.makeText(getApplication(),"파일생성실패",Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplication(),"파일생성실패",Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.button7:

                try {
                    FileInputStream fos = new FileInputStream(filename);
                    byte arr[] =new byte[fos.available()];
                    fos.read(arr);
                    fos.close();
                    String str = new String(arr);
                    Toast.makeText(getApplication(),"파일내용"+str+"성공",Toast.LENGTH_SHORT).show();

                } catch (FileNotFoundException e) {
                    Toast.makeText(getApplication(),"파일읽기실패",Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplication(),"폴더삭제완료",Toast.LENGTH_SHORT).show();
                break;





        }

    }






    String TAG = "[TEST]";
    public  boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v(TAG,"Permission is granted");
                return true;
            } else {

                Log.v(TAG,"Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v(TAG,"Permission is granted");
            return true;
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults[0]== PackageManager.PERMISSION_GRANTED){
            Log.v(TAG,"Permission: "+permissions[0]+ "was "+grantResults[0]);
            //resume tasks needing this permission
        }
    }

}
