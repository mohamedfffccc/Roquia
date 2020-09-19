package com.roqua;


import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class PlayerFragment extends BaseFragment {

    String path, name;
    Intent intent;
    MediaPlayer mp = new MediaPlayer();
    @BindView(R.id.playr_ivpreview)
    ImageView playrIvpreview;
    @BindView(R.id.player_sbprogress)
    SeekBar playerSbprogress;
    @BindView(R.id.player_btnplay)
    ImageView playerBtnplay;

    Player player = new Player();
    boolean play = false;
    //    @BindView(R.id.player_btshare)
//    ImageView playerBtshare;
//    @BindView(R.id.facebook)
//    CircleImageView facebook;
//    @BindView(R.id.whatsup)
//    CircleImageView whatsup;
//    @BindView(R.id.twitter)
//    CircleImageView twitter;
//    @BindView(R.id.instagram)
//    CircleImageView instagram;
//    @BindView(R.id.pinteret)
//    CircleImageView pinteret;
    String facebooku = "com.facebook.katana";
    String twitteru = "com.twitter.android";
    String instagramu = "com.instagram.android";
    String pinterestu = "com.pinterest";
    String whatsupu = "com.whatsapp";

    @BindView(R.id.player_tv_surah_name)
    TextView playerTvSurahName;
    @BindView(R.id.btn_share)
    ImageView btnShare;
    @BindView(R.id.btn_rate)
    ImageView btnRate;
    @BindView(R.id.rel1)
    RelativeLayout rel1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_player, container, false);
        ButterKnife.bind(this, root);
        playerTvSurahName.setText(name);


        setUpActivity();
        player.start();
        playerSbprogress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser && mp != null) {
                    mp.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });


        return root;
    }



    @OnClick({R.id.btn_share, R.id.btn_rate, R.id.player_btnplay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_share:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_SUBJECT, "الرقية الشرعية للشيخ " + name);
                sendIntent.putExtra(Intent.EXTRA_TEXT, path);
                sendIntent.setType("img/plain");
                startActivity(Intent.createChooser(sendIntent, "اختار التطبيق الذي مشاركة النص معه :"));
                break;
            case R.id.btn_rate:
                Intent val_i = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + BuildConfig.APPLICATION_ID));
                startActivity(val_i);
                break;
            case R.id.player_btnplay:

                if (play == false) {
                    playerBtnplay.setBackgroundResource(R.drawable.img_btn_pause_pressed);
                    mp.start();
                    play = true;
                } else if (play == true) {
                    playerBtnplay.setBackgroundResource(R.drawable.img_btn_play_pressed);
                    mp.pause();
                    play = false;
                }
                break;
        }
    }


    class Player extends Thread {
        @Override
        public void run() {
            try {
                mp.setDataSource(path);
                try {
                    mp.prepare();
                } catch (Exception e) {
                }
                playerSbprogress.setMax(mp.getDuration());

            } catch (IOException e) {
                e.printStackTrace();
            }
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (getActivity() == null) {
                    return;
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        playerSbprogress.setProgress(mp.getCurrentPosition());
                    }
                });
            }
        }
    }

    @Override
    public void onBack() {

        player.interrupt();
        super.onBack();
    }


}
