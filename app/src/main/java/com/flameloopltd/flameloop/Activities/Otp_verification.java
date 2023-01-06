package com.flameloopltd.flameloop.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.flameloopltd.flameloop.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import in.aabhasjindal.otptextview.OTPListener;
import in.aabhasjindal.otptextview.OtpTextView;

public class Otp_verification extends AppCompatActivity {

    TextView coutdowtxt , resendcodebtn;
    private OtpTextView otpTextView;

    Button optbtn;
    int counter;
    String verificationId;
    String otpfinal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification);
        getSupportActionBar().hide();
        otpTextView = findViewById(R.id.otp_view);

        otpTextView.requestFocus();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);


        otpTextView.setOtpListener(new OTPListener() {
            @Override
            public void onInteractionListener() {

            }

            @Override
            public void onOTPComplete(String otp) {

                otpfinal = otp;

                completeverification();



            }
        });





        verificationId = getIntent().getExtras().getString("verificationid");

        Toast.makeText(this, verificationId, Toast.LENGTH_SHORT).show();



        coutdowtxt = findViewById(R.id.counter_txt);
        resendcodebtn = findViewById(R.id.resendcodebtn);
        optbtn = findViewById(R.id.optreview);


        optbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                completeverification();


            }
        });

        new CountDownTimer(20000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                String te = "Did't Receive the code yet ? Click on resent in ";
                String timee = (String.valueOf(counter));

                coutdowtxt.setText(te+timee+" sec");
                counter++;
            }
            @Override
            public void onFinish() {

                resendcodebtn.setTextColor(Color.parseColor("#0074CC"));



            }
        }.start();
}

    private void completeverification() {

        if (verificationId !=null){

            PhoneAuthCredential phoneAuthcred  = PhoneAuthProvider.getCredential(verificationId,otpfinal);

            FirebaseAuth.getInstance().signInWithCredential(phoneAuthcred).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()){

                        Toast.makeText(Otp_verification.this, "Done", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),Setup_Profile.class);
                        startActivity(intent);
                        finish();
                    }

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    Toast.makeText(Otp_verification.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });
        }

    }


}