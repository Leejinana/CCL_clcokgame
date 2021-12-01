package com.lovehp30.clockgame;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import com.lovehp30.clockgame.databinding.ActivityMainBinding;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    int[][] map;
    ImageView step[];
    int pivot=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //start setting
        map = new int[10][3];
        createMap();
        setClockBoard(map[pivot][0],map[pivot][1],map[pivot][2]);
        step = setMoveBarImages();
        Log.e("Step",step[0].getDrawable().toString());





        //btn setOnClick

        binding.btnMainBack.setOnClickListener(v->{
            startActivity(new Intent(getApplicationContext(),StartActivity.class));
            finish();
        });
    }
    private void createMap(){
        Random rand = new Random();
        int i=0;
        while (i<10){
            if(i<3){
                map[i][0] = rand.nextInt(11)+1;
                map[i][1] = 0;
                map[i][2] = rand.nextInt(2);
            }else if(i<6){
                map[i][0] = rand.nextInt(11)+1;
                map[i][1] = (rand.nextInt(11)+1)*5;
                map[i][2] = rand.nextInt(2);
            }else{
                map[i][0] = rand.nextInt(11)+1;
                map[i][1] = rand.nextInt(59)+1;
                map[i][2] = rand.nextInt(2);
            }
            i++;
        }
    }
    @SuppressLint("SetTextI18n")
    private void setClockBoard(int h, int y, int turn){
        binding.clockBoard.updateHand(h,y);
        if(turn == 0){
            binding.playBtnLeft.setText(h+":"+y);
            binding.playBtnRight.setText(getMinToHour(y)+":"+getHourToMin(h,y));
        }else{
            binding.playBtnRight.setText(h+":"+y);
            binding.playBtnLeft.setText(getMinToHour(y)+":"+getHourToMin(h,y));
        }
        binding.clockBoard.invalidate();
    }
    public void clickBtnEvent(View v) {
        boolean correct;
        if (map[pivot][2] == 1) {
            if (v == binding.playBtnRight) correct = true;
            else correct = false;
        } else {
            if (v == binding.playBtnLeft) correct = true;
            else correct = false;
        }
        popupDialog(correct);
        if (correct) {//정답 이벤트
//            Toast.makeText(getApplicationContext(), "정답", Toast.LENGTH_LONG).show();
            pivot++;
            Log.e("Step",step[pivot].getId()+"");

            setClockBoard(map[pivot][0], map[pivot][1], map[pivot][2]);
            setCatMove();

        } else {
            Toast.makeText(getApplicationContext(), "오답", Toast.LENGTH_LONG).show();


        }
    }
    private void popupDialog(boolean flag){
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        if(flag)dialog.setContentView(R.layout.dialog_y);
        else dialog.setContentView(R.layout.dialog_n);
        dialog.show();
        new Thread(() -> {
            try {
                Thread.sleep(1000);
                dialog.dismiss();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void setCatMove(){
        if(pivot==9)//last
        {
            step[pivot].setImageResource(R.drawable.move_end);
            step[pivot].setMaxHeight(binding.catMove.getHeight());
        }else
            step[pivot].setImageResource(R.drawable.move_cat);
        step[pivot-1].setImageBitmap(null);//이전 이미지 삭제
    }
    private ImageView[] setMoveBarImages(){
        ImageView imageView[] = new ImageView[10];
        imageView[0] = binding.move1;
        imageView[1] = binding.move2;
        imageView[2] = binding.move3;
        imageView[3] = binding.move4;
        imageView[4] = binding.move5;
        imageView[5] = binding.move6;
        imageView[6] = binding.move7;
        imageView[7] = binding.move8;
        imageView[8] = binding.move9;
        imageView[9] = binding.move10;
        return imageView;
    }
    public int getHourToMin(int hour,int min){
        return hour*5+(int)(min*0.09);
    }
    public int getMinToHour(int min){
        return min/5==0?12:min/5;
    }
}