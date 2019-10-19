package com.example.numberslist;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

// Фрагмент списка чисел
public class ListFragment extends Fragment implements NumbersListAdapter.ItemClickListener {

    private NumbersListAdapter adapter;

    // Имя листа, по которому можно будет обратиться к нему в бандле
    // при сохранении стейта фрагмента
    private final String onSaveStateListName = "currentList";
    private Integer onSaveStateMaxNumber = 100;
    private RecyclerView recyclerView;

    // Конструктор фрагмента
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            onSaveStateMaxNumber = savedInstanceState.getInt(onSaveStateListName);
        }
        View view = inflater.inflate(R.layout.number_list, container, false);
        recyclerView = view.findViewById(R.id.recycleList);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), getCollumnCount());
        recyclerView.setLayoutManager(gridLayoutManager);

        adapter = new NumbersListAdapter(getContext(), onSaveStateMaxNumber);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

        Button button = view.findViewById(R.id.addButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.AddNumber();
                onSaveStateMaxNumber++;
                recyclerView.smoothScrollToPosition(adapter.getItemCount() - 1);
            }
        });

        recyclerView.smoothScrollToPosition(adapter.getItemCount() - 1);

        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(onSaveStateListName, onSaveStateMaxNumber);
    }

    @Override
    public void onItemClick(View view, int position) {

        Log.d("[INF]", Integer.toString(position));

        ((MainActivity)getActivity()).setCurrentNumberFragment(position + 1);
    }

    // Возвращает кольчество чисел в строке в стеке в зависимомти от ориентации
    // экрана
    private int getCollumnCount() {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            return 3;
        }
        return 4;
    }
}
