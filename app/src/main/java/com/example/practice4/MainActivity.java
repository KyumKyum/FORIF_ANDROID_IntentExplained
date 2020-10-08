package com.example.practice4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText editText = findViewById(R.id.ET_Input);
        Button button = findViewById(R.id.BTN_Click);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputValue = editText.getText().toString();
                //자, 버튼을 누르면 해당 EditText에 들어간 값을 String value로 가져올거에요!

                //Intent는 일종의 상자라고 생각하세요! 자 쉽게 해볼게요
                //여러분은 이제 SubActivity로 소포를 보낼거에요! 그러면 먼저 SubActivity로 가는 우표가 필요하겠죠!

                Intent intent = new Intent(MainActivity.this,SubActivity.class);
                //발송지는 MainActivity, 목적지는 SubActivity라고 명시를 해줍니다!

                intent.putExtra("INPUT_TEXT",inputValue);
                //EditText로 받아온 String value를 해당 intent에 넣어줍니다.
                //자, 여기서 데이터가 하나면 모르겠는데, 데이터가 여러개일 경우, 구분을 어떻게 할까요?
                //쿠팡 물류 센터에서 각 ID값으로 물건을 구분하듯이, Key 값으로 각 데이터들을 구분합니다!
                //여기서 key값은 INPUT_TEXT, 나중에 다시 이 데이터를 추출할때, INPUT_TEXT로 찾겠다는거죠.
                startActivityForResult(intent,101);
                //자, subActivity로 소포를 보냅니다! 해당 액티비티에서 돌아오는 결과는 101이라는 requestcode로 구분할거에요!
                //SubActivity.java로 가주세요!
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //자 불렀던 Activity가 종료되고, 결과를 받아올때 그 결과는 이 메소드에서 처리합니다.
        //여기서 전송된 intent가 있으면, 저절로 해당 intent는 parameter에 있는 data에 저장되게 됩니다.
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 101 && resultCode == RESULT_OK  && data != null){
            //만약 subActivity에서 돌아왔고(101), result가 ok하며, (setResult), 해당 액티비티에서 전송된 인텐트가 있으면
            String returnValue = data.getStringExtra("RETURN_TEXT");
            //String value를 추출하구, (RETURN_TEXT : Android!)
            Toast.makeText(getApplicationContext(),returnValue,Toast.LENGTH_SHORT).show();
            //해당 value를 toast로 출력합니다!

            //진짜 어려운 내용 맞아요ㅠㅠ 이해가 안되시면 언제든 물어봐줘요!
        }
    }
}