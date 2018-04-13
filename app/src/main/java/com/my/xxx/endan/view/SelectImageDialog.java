package com.my.xxx.endan.view;

import android.app.Activity;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.my.xxx.endan.view.NsButton;
import com.my.xxx.endan.R;


public class SelectImageDialog {
    private Activity context;
    private Dialog dialog;
    private NsButton btn_picture, btn_photo, btn_cancle;
    private OnSelect select;

    public void setSelect(OnSelect select) {
        this.select = select;
    }

    public SelectImageDialog(Activity context) {
        this.context = context;
    }

    public void show() {
        if (dialog == null) {
            init();
        }
        try {
            dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init() {
        View view = LayoutInflater.from(context).inflate(R.layout.photo_choose_dialog, null);
        dialog = new Dialog(context, R.style.transparentFrameWindowStyle);
        dialog.setContentView(view, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        Window window = dialog.getWindow();
        // 设置显示动画
        window.setWindowAnimations(R.style.main_menu_animstyle);
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.x = 0;
        wl.y = context.getWindowManager().getDefaultDisplay().getHeight();
        // 以下这两句是为了保证按钮可以水平满屏
        wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
        wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;

        // 设置显示位置
        dialog.onWindowAttributesChanged(wl);
        // 设置点击外围解散
        dialog.setCanceledOnTouchOutside(true);

        btn_picture = (NsButton) window.findViewById(R.id.btn_picture);
        btn_photo = (NsButton) window.findViewById(R.id.btn_photo);
        btn_cancle = (NsButton) window.findViewById(R.id.btn_cancle);
        //图库选择
     /*   btn_picture.setOnClickListener(v -> {
            if (select != null) {
                select.openGallery();
            }
            dialog.dismiss();
        });*/
        btn_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (select != null) {
                    select.openGallery();
                }
                dialog.dismiss();
            }
        });
        //拍照选择
   /*     btn_photo.setOnClickListener(v -> {
            if (select != null) {
                select.openCamera();
            }
            dialog.dismiss();
        });*/
        btn_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (select != null) {
                    select.openCamera();
                }
                dialog.dismiss();
            }
        });
        //取消
        //btn_cancle.setOnClickListener(v -> dialog.dismiss());
        btn_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    public interface OnSelect {
        void openCamera();

        void openGallery();
    }
}
