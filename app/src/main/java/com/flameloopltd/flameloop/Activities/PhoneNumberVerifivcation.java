package com.flameloopltd.flameloop.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.flameloopltd.flameloop.R;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class PhoneNumberVerifivcation extends AppCompatActivity {

    TextView coutdowtxt , resendcodebtn;

    Button optbtn;
    EditText phonenumbertxt;
    String phonenumber;
    private FirebaseAuth mAuth;

    int counter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_number_verifivcation);
        getSupportActionBar().hide();

        optbtn = findViewById(R.id.getotpbtn);
        phonenumbertxt = findViewById(R.id.phonenumnber_txt);
        mAuth = FirebaseAuth.getInstance();

        FirebaseAuth.getInstance().getFirebaseAuthSettings().forceRecaptchaFlowForTesting(false);

        phonenumbertxt.requestFocus();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);











        optbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                phonenumber = phonenumbertxt.getText().toString().trim();

                checkutils();

            }
        });
//
//        coutdowtxt = findViewById(R.id.counter_txt);
//        resendcodebtn = findViewById(R.id.resendcodebtn);
//
//        new CountDownTimer(20000,1000) {
//            @Override
//            public void onTick(long millisUntilFinished) {
//
//                String te = "Did't Receive the code yet ? Click on resent in ";
//                String timee = (String.valueOf(counter));
//
//                coutdowtxt.setText(te+timee+" sec");
//                counter++;
//            }
//            @Override
//            public void onFinish() {
//
//                resendcodebtn.setTextColor(Color.parseColor("#0074CC"));
//
//
//
//            }
//        }.start();
    }

    private void checkutils() {

        if (TextUtils.isEmpty(phonenumber)){

            Toast.makeText(this, "Enter your phone number", Toast.LENGTH_SHORT).show();
        }

        else if(phonenumber.length() == 10){

            startbtn();





        }

        else {

            Toast.makeText(this, "Enter a correct phone number", Toast.LENGTH_SHORT).show();
        }



    }

    private void startbtn() {


        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber("+91"+phonenumber)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {

                            }


                            @Override
                            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                super.onCodeSent(s, forceResendingToken);
                                Intent intent = new Intent(PhoneNumberVerifivcation.this,Otp_verification.class);
                intent.putExtra("verificationid",s);
                startActivity(intent);
                            }
                        })          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);

//
//        PhoneAuthProvider.getInstance().verifyPhoneNumber("+91"+phonenumber,60,TimeUnit.SECONDS, PhoneNumberVerifivcation.this, new PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
//
//
//            @Override
//            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
//
//            }
//
//            @Override
//            public void onVerificationFailed(@NonNull FirebaseException e) {
//
//            }
//
//
//            @Override
//            public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
//                super.onCodeSent(verificationId, forceResendingToken);
//
//                Intent intent = new Intent(PhoneNumberVerifivcation.this,Otp_verification.class);
//                intent.putExtra("phone","+91"+phonenumber);
//                startActivity(intent);
//
//
//            }
//        } );









    }


}
