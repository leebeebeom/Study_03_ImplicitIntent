package com.beebeom.a03_impicitintent;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editTextPhoneNumber = findViewById(R.id.et_phoneNumber);

        //클릭하면 폰에 설정된 기본 전화어플이 실행됨.
        findViewById(R.id.btn_dial).setOnClickListener(v -> {
            //인텐트에 액션을 지정해줌으로써 직접적으로 무언가를 지정해주지 않고
            //액션을 실행할 수 있다.
            //이걸 암시적 인텐트라고 함.
            Intent intent = new Intent(Intent.ACTION_DIAL);

            //전화걸기 인텐트는 전화번호를 넣어줘야 함.
            String uri = editTextPhoneNumber.getText().toString();

            //데이터는 tel: 형식으로 넣어주면 작동 됨.
            //또 uri 타입으로 넣어줘야 하기 때문에 Uri.parse를 붙여줌
            intent.setData(Uri.parse("tel:"+uri));

            //중요!!
            //이 액션을 실행할 어플이 없다면 앱이 죽어버리기 때문에
            //꼭 if 문으로 먼저 체크를 해줘야함
            if(intent.resolveActivity(getPackageManager()) != null){
                startActivity(intent);
            }else{
                Toast.makeText(this, "이 액션을 수행할 애플리케이션이 없습니다", Toast.LENGTH_SHORT).show();
            }
        });
    }
}