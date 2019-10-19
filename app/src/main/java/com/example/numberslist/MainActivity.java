package com.example.numberslist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    // Фрагмент списка чисел
    Fragment listFragment;
    // Фрагмент конкретного числа
    Fragment currentNumberFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setListFragment(savedInstanceState);
    }

    // Вызов фрагмента списка
    public void setListFragment(Bundle savedInstanceState) {
        // Если сессия новая
        if (savedInstanceState == null) {

            Log.d("[INF]", "InstanceState == null");

            // Создаем новый фрагмент
            listFragment = new ListFragment();

            // Получаем FragmnetManager, который позволяет взаимодействовать
            // фрагментам, которые ассоциированны с этой активити
            getSupportFragmentManager()
                    // Открываем транзакцию
                    .beginTransaction()
                    // Заменяем фрагмент, который находится в fragment_container
                    // на listFragment Фрагмент
                    .replace(R.id.fragment_container, listFragment)
                    // Запоминам транзакцию после ее коммита, таким образом восстанавливаем
                    // состояние приложения после смерти в фоне
                    .addToBackStack(null)
                    // Коммитим изменения
                    .commit();
        // Иначе, открываем список чисел
        } else {
            Log.d("[INF]", "InstanceState != null");

            listFragment = getSupportFragmentManager()
                    .getFragment(savedInstanceState, "listFragment");

            Log.d("[INF]", "Success getting fragment");
        }
    }

    public void setCurrentNumberFragment(int currentNumber) {
        if (currentNumberFragment == null) {
            currentNumberFragment = new NumberFragment();
        }

        Bundle bundle = new Bundle();
        bundle.putInt("currentNumber", currentNumber);

        currentNumberFragment.setArguments(bundle);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, currentNumberFragment)
                .addToBackStack(null)
                .commit();
    }
}
