package com.example.practice4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        TextView textView = findViewById(R.id.TV_Text);

        Intent receivedIntent = getIntent();
        //전송된 데이터를 받아올거에요! 해당 액티비티가 시작했을 때, 전송된 인텐트가 있으면 여기에 저장할거에요.
        String receivedString = receivedIntent.getStringExtra("INPUT_TEXT");
        //전달된 데이터에서 key값이 INPUT_TEXT인 데이터를 receivedString에 저장할거에요.
        //앞서 edittext에서 추출한 값을 INPUT_TEXT로 저장했으니, receivedText == 사용자가 입력한 값

        textView.setText(receivedString);
        //그 값을 textView로 설정해줬어요!

        //이제 이 Activity를 종료할거에요.
        //종료하는 방법은 간단하게 finish()를 하면 해당 Activity가 종료되게 됩니다.
        //근데, 따로 버튼을 만들고, listener를 설정하기 귀찮기도 하고, 무엇보다 우리는 뒤로가기 버튼을 누리잖아요!
        //그 뒤로가기 버튼을 주관하는 함수가 바로 onBackPressed이에요!
    }

    @Override
    public void onBackPressed() {
        //해당 메소드는 뒤로가기 버튼이 눌릴때마다 실행이 됩니다!
        String stringValue = "Android!";
        Intent intent = new Intent();
        //해당 액티비티가 꺼짐 -> 목적지는 이 액티비티가 실행되기 전의 화면
        //즉, 목적지가 이처럼 따로 설정해 줄 필요가 없는 경우는, 목적지를 넣을 필요가 없어요!
        //간단하게 이야기해서, Activity도 일종의 Layer인거에요.
        //MainActivity위에 SubActivity가 올라가는데, 해당 Activity가 종료되면 자연스럽게 아래에 있던 MainActivity가 불려지게 되겠죠.
        //만약 다른 Activity로 가고 싶을 때에는 똑같이 시작하는 장소와 목적지를 입력해줘야 합니다.
        intent.putExtra("RETURN_TEXT",stringValue);
        //설정한 StringValue를 넣어주고요,
        setResult(RESULT_OK,intent);
        //해당 intent는 이제 완전하게 보내졌다고 결과를 설정해줍니다! 일종의 안전장치의 개념이에요.
        super.onBackPressed();//finish();
        //해당 액티비티가 종료되면, 다시 원래 MainActivity로 돌아가게 돼요. 해당 Activity는 결과를 이제 받겠죠?
        //해당 결과를 받는 메소드가 바로 onActivityResult입니다.
        //MainActivity의 onActivityResult로 가주세요!
    }
}