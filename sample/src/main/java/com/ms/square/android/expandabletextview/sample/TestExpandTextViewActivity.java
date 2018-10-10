package com.ms.square.android.expandabletextview.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ms.square.android.expandabletextview.sample.phong.CustomExpandableTextView;
import com.ms.square.android.mymodule.app.R;

public class TestExpandTextViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_expand_text_view);
        final CustomExpandableTextView textView = findViewById(R.id.textView);

        textView.setCollapseText("The group debuted on August 8, 2016, with their single Square One, which spawned \"Whistle,\" their first number-one song in South Korea. " +
                "The single also included \"Boombayah,\" their first number one on Billboard World Digital Songs chart");
        textView.setExpandText("The group debuted on August 8, 2016, with their single Square One, which spawned \"Whistle,\" their first number-one song in South Korea. " +
                "The single also included \"Boombayah,\" their first number one on Billboard World Digital Songs chart, which set the record as the most-viewed debut music video by a Korean act. With the group's commercial success in their first five months, they were hailed as the New Artist of the Year at the 31st Golden Disc Awards and the 26th Seoul Music Awards."
                + "The single also included \"Boombayah,\" their first number one on Billboard World Digital Songs chart, " +
                        "which set the record as the most-viewed debut music video by a Korean act. With the group's commercial success in their first five months, they were hailed as the New Artist of the Year at the 31st Golden Disc Awards and the 26th Seoul Music Awards."
                + "The single also included \"Boombayah,\" their first number one on Billboard World Digital Songs chart, " +
                "which set the record as the most-viewed debut music video by a Korean act. With the group's commercial success in their first five months, they were hailed as the New Artist of the Year at the 31st Golden Disc Awards and the 26th Seoul Music Awards."
        );
        textView.setText("\"The group debuted on August 8, 2016, with their single Square One, which spawned \\\"Whistle,\\\" their first number-one song in South Korea. \" +\n" +
                "                \"The single also included \\\"Boombayah,\\\" their first number one on Billboard World Digital Songs chart\"");
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.toggle();
            }
        });
    }
}
