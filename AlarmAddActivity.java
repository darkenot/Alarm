package com.example.mi_zaft.alarmplus.AddAlarm;

        import java.io.BufferedReader;
        import java.io.BufferedWriter;
        import java.io.File;
        import java.io.FileNotFoundException;
        import java.io.FileReader;
        import java.io.FileWriter;
        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.io.OutputStreamWriter;

        import android.os.Environment;
        import android.util.Log;

        import android.app.Activity;
        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.EditText;
        import android.widget.ImageButton;
        import android.widget.TimePicker;
        import android.widget.Toast;

        import com.example.mi_zaft.alarmplus.MainActivity;
        import com.example.mi_zaft.alarmplus.R;


public class AlarmAddActivity extends Activity {

    final String LOG_TAG = "myLogs";

    final String FILENAME = "file";

    final String DIR_SD = "MyFiles";
    final String FILENAME_SD = "fileSD";




    ImageButton AlarmAddBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_add);
        TimePicker tpHourMin = (TimePicker) findViewById(R.id.timePickerAdd); tpHourMin.setIs24HourView(true);



        AlarmAddBack = (ImageButton) findViewById(R.id.alarm_add_back);
        View.OnClickListener oclAlarmAddBack = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(AlarmAddActivity.this, MainActivity.class));
            }
        };

        AlarmAddBack.setOnClickListener(oclAlarmAddBack);
    }

    public void onclick(View v) {
        switch (v.getId()) {
            case R.id.accept_alarm_add:
                writeFile();
                break;
            case R.id.alarm_add_back:
                readFile();
                break;
        }
    }

    void writeFile() {
        try {
            final EditText NameAlarmEdit = (EditText) findViewById(R.id.AlarmNameAdd);
            String NameAlarm = NameAlarmEdit.getText().toString();
            // отрываем поток для записи
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                    openFileOutput(FILENAME, MODE_PRIVATE)));
            // пишем данные
            bw.write(NameAlarm);
            // закрываем поток
            bw.close();
            Log.d(LOG_TAG, NameAlarm);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFile() {
        try {
            // открываем поток для чтения
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    openFileInput(FILENAME)));
            String str = "";
            // читаем содержимое
            while ((str = br.readLine()) != null) {
                Log.d(LOG_TAG, str);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
