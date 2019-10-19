package com.example.numberslist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    // Фрагмент списка чисел
    private Fragment listFragment;
    // Фрагмент конкретного числа
    private Fragment currentNumberFragment;
    // Имя фрагмента листа
    private final static String listFragmentName = "listFragment";

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
                    // Коммитим изменения
                    .commit();
        // Иначе, открываем список чисел
        } else {
            listFragment = getSupportFragmentManager()
                    .getFragment(savedInstanceState, listFragmentName);

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
                // Запоминам транзакцию после ее коммита, таким образом восстанавливаем
                // состояние приложения после смерти в фоне
                .addToBackStack(null)
                .commit();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        getSupportFragmentManager().
                putFragment(outState, listFragmentName, listFragment);
    }
}
