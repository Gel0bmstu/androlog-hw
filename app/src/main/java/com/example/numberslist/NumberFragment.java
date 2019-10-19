package com.example.numberslist;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class NumberFragment extends Fragment {
    private Integer currentNumber;
    private final static String currentNumberName = "currentNumber";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Получаем
        Bundle arguments = getArguments();

        if (arguments != null) {
            currentNumber = getArguments().getInt(currentNumberName, 0);
        } else {
            currentNumber = 0;
        }

        View view = inflater.inflate(R.layout.current_nubmer, container, false);

        TextView textView = view.findViewById(R.id.current_number_text);

        if (currentNumber % 2 == 0) {
            textView.setTextColor(ContextCompat.getColor(getContext(), R.color.colorEven));
        } else {
            textView.setTextColor(ContextCompat.getColor(getContext(), R.color.colorOdd));
        }
        textView.setText(String.valueOf(currentNumber));

        return view;
    }
}
