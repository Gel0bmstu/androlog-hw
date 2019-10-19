package com.example.numberslist;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import static android.app.PendingIntent.getActivity;


public class NumbersListAdapter extends RecyclerView.Adapter<NumbersListAdapter.ViewHolder>  {

    // Массив чисел
    private List<Integer> mData;
    // Преобразователь Layout -> View
    private LayoutInflater mInflater;
    // Обработчик нажатий на list_layout
    private ItemClickListener mClickListener;
    // Контекст активити для получения цвета из res/colors
    private Context mContext;

    // Конструктор, принимающий конткст для создания Inflanter'a
    public NumbersListAdapter(Context c, Integer maxNum) {
        mInflater = LayoutInflater.from(c);
        mContext = c;
        generarteData(maxNum);
    }

    // Вьюхолдер для числа
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView mNumberView;

        ViewHolder(View itemView) {
            super(itemView);
            mNumberView = itemView.findViewById(R.id.number_item);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    private void generarteData(Integer maxNum) {

        mData = new ArrayList<>();

        for (int i = 1; i <= maxNum; i++) {
            mData.add(i);
        }
    }

    // "ПредКонструктор" вьюшки каждого числа
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.number_item, parent, false);
        return new ViewHolder(view);
    }

    // Рисуте число на определенной позиции
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // Получаем число из датасета по индексу (position)
        Integer number = mData.get(position);

        // Красим число в определенный цвет
        holder.mNumberView.setText(Integer.toString(number));
        if (number % 2 == 0) {
            holder.mNumberView.setTextColor(ContextCompat.getColor(mContext, R.color.colorEven));
        } else {
            holder.mNumberView.setTextColor(ContextCompat.getColor(mContext, R.color.colorOdd));
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    // Добавлям число в датасет, говорим вьюхолжеру: "датасет обновился,
    // перерисуй вьюшки, алло"
    public void AddNumber() {
        mData.add(mData.size() + 1);
        notifyDataSetChanged();
    }

    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}

