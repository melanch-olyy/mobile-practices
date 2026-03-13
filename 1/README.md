# Отчет по практической работе №1

**Выполнил:** Студент группы БСБО-09-23 

**ФИО:** Самсонова Ольга Павловна

---

## 1. Цель работы
Научиться создавать мобильные приложения в Android Studio. Изучить архитектуру проекта, различные типы layout'ов, систему ресурсов, жизненный цикл Activity и обработку нажатий кнопок на языке Java.

## 2. Архитектура проекта
Создан мульти-модульный проект со следующими компонентами:
1. **`layouttype`** — эксперименты с LinearLayout и TableLayout
2. **`control_lesson1`** — ConstraintLayout и адаптация под поворот экрана  
3. **`ButtonClicker`** — реализация обработчиков событий

Также был настроен эмулятор android-устройства для тестирования
---

## 3. Ход работы

### 3.1. Модуль `layouttype` — базовые контейнеры

#### LinearLayout
Был создан файл `linear_layout.xml`. В нем реализована структура из корневого вертикального контейнера, который включает в себя два горизонтальных ряда. Для того чтобы кнопки имели одинаковый размер и заполняли экран равномерно, был применен атрибут веса `android:layout_weight="1"`.

**Код `linear_layout.xml`:**
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <LinearLayout android:layout_width="match_parent" android:layout_height="wrap_content" android:orientation="horizontal">
        <Button android:id="@+id/button3" android:layout_width="0dp" android:layout_height="wrap_content" android:layout_weight="1" android:text="Button" />
        <Button android:id="@+id/button2" android:layout_width="0dp" android:layout_height="wrap_content" android:layout_weight="1" android:text="Button" />
        <Button android:id="@+id/button" android:layout_width="0dp" android:layout_height="wrap_content" android:layout_weight="1" android:text="Button" />
    </LinearLayout>

    <LinearLayout android:layout_width="match_parent" android:layout_height="wrap_content" android:orientation="horizontal">
        <Button android:id="@+id/button4" android:layout_width="0dp" android:layout_height="wrap_content" android:layout_weight="1" android:text="Button" />
        <Button android:id="@+id/button5" android:layout_width="0dp" android:layout_height="wrap_content" android:layout_weight="1" android:text="Button" />
        <Button android:id="@+id/button6" android:layout_width="0dp" android:layout_height="wrap_content" android:layout_weight="1" android:text="Button" />
    </LinearLayout>
</LinearLayout>
```

#### TableLayout
Во втором файле `table_layout.xml` была собрана табличная верстка с помощью строк TableRow. В ячейки помещены кнопки, текстовые метки, чекбокс и графическая кнопка ImageButton с системной иконкой. 
**Код `table_layout.xml`:**
```xml
<?xml version="1.0" encoding="utf-8"?>
<TableLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <TableRow android:layout_width="match_parent" android:layout_height="match_parent">
        <Button android:id="@+id/button7" android:layout_width="wrap_content" android:layout_height="wrap_content" android:layout_weight="1" android:text="Button" />
        <TextView android:id="@+id/textView3" android:layout_width="wrap_content" android:layout_height="wrap_content" android:layout_weight="1" android:text="This is Table View!" />
        <Button android:id="@+id/button8" android:layout_width="wrap_content" android:layout_height="wrap_content" android:layout_weight="1" android:text="Button" />
    </TableRow>

    <TableRow android:layout_width="match_parent" android:layout_height="match_parent">
        <Button android:id="@+id/button10" android:layout_width="0dp" android:layout_height="wrap_content" android:layout_weight="2" android:text="Button" />
        <CheckBox android:id="@+id/checkBox" android:layout_width="0dp" android:layout_height="wrap_content" android:layout_weight="2" android:text="CheckBox" />
    </TableRow>

    <TableRow android:layout_width="match_parent" android:layout_height="match_parent">
        <ImageButton android:id="@+id/imageButton" android:layout_width="wrap_content" android:layout_height="wrap_content" android:layout_weight="3" app:srcCompat="@android:drawable/ic_lock_power_off" />
        <Button android:id="@+id/button12" android:layout_width="wrap_content" android:layout_height="wrap_content" android:layout_weight="3" android:text="Button" />
        <Button android:id="@+id/button13" android:layout_width="wrap_content" android:layout_height="wrap_content" android:layout_weight="3" android:text="Button" />
    </TableRow>
</TableLayout>
```
### 3.2. Модуль `control_lesson1` — ConstraintLayout

#### Карточка контакта Mr. Fish
В `activity_main.xml` реализована адаптивная верстка карточки контакта с аватаром, полями и кнопкой. Все компоненты интерфейса (фотография, иконка телефона, текстовые поля и кнопка сохранения) были надежно зафиксированы друг относительно друга с помощью системы привязок (constraints).

**Код `activity_main.xml`:**
```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/main"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <ImageView android:id="@+id/imageView" android:layout_width="0dp" android:layout_height="500dp"
        app:layout_constraintEnd_toEndOf="parent" app:layout_constraintStart_toStartOf="parent" 
        app:layout_constraintTop_toTopOf="parent" app:srcCompat="@drawable/fish" />

    <TextView android:id="@+id/textView2" android:layout_width="wrap_content" android:layout_height="wrap_content"
        android:layout_marginStart="16dp" android:text="Name:" android:textSize="22sp"
        app:layout_constraintStart_toStartOf="parent" app:layout_constraintTop_toBottomOf="@+id/imageView" />

    <TextView android:id="@+id/textView3" android:layout_width="wrap_content" android:layout_height="wrap_content"
        android:layout_marginStart="92dp" android:text="Mr. Fish" android:textSize="22sp"
        app:layout_constraintStart_toEndOf="@+id/textView2" app:layout_constraintTop_toBottomOf="@+id/imageView" />

    <TextView android:id="@+id/textView4" android:layout_width="wrap_content" android:layout_height="wrap_content"
        android:layout_marginStart="16dp" android:layout_marginTop="24dp" android:text="Organisation:" android:textSize="22sp"
        app:layout_constraintStart_toStartOf="parent" app:layout_constraintTop_toBottomOf="@+id/textView2" />

    <TextView android:id="@+id/textView5" android:layout_width="wrap_content" android:layout_height="wrap_content"
        android:layout_marginStart="24dp" android:layout_marginTop="24dp" android:text="MIREA" android:textSize="22sp"
        app:layout_constraintStart_toEndOf="@+id/textView4" app:layout_constraintTop_toBottomOf="@+id/textView3" />

    <ImageView android:id="@+id/imageView2" android:layout_width="31dp" android:layout_height="29dp"
        android:layout_marginStart="16dp" android:layout_marginTop="24dp"
        app:layout_constraintStart_toStartOf="parent" app:layout_constraintTop_toBottomOf="@+id/textView4"
        app:srcCompat="@drawable/baseline_phone_24" />

    <TextView android:id="@+id/textView7" android:layout_width="wrap_content" android:layout_height="wrap_content"
        android:layout_marginStart="124dp" android:layout_marginTop="24dp" android:text="+7 (911)911-91-91" android:textSize="22sp"
        app:layout_constraintStart_toEndOf="@+id/imageView2" app:layout_constraintTop_toBottomOf="@+id/textView5" />

    <Button android:id="@+id/button" android:layout_width="wrap_content" android:layout_height="wrap_content"
        android:layout_marginEnd="16dp" android:layout_marginBottom="16dp" android:backgroundTint="#757878" android:text="Save"
        app:layout_constraintBottom_toBottomOf="parent" app:layout_constraintEnd_toEndOf="parent" />
</androidx.constraintlayout.widget.ConstraintLayout>
```

#### Поддержка Landscape
Создана activity_second.xml (land) для альбомного режима. Для нее кнопки были передвинуты такб чтобы каждую из них было видно в альбомном режиме.
В MainActivity.java указан setContentView(R.layout.activity_second) для переопределения стартового экрана. 

### 3.3. Модуль `ButtonClicker` — обработка событий
В финальной части работы была написана бизнес-логика приложения на Java. Задача заключалась в получении ссылок на графические элементы и настройке реакций на действия пользователя.

На экране размещены: текстовое поле (tvOut), две интерактивные кнопки (btnWhoAmI и btnItIsNotMe) и CheckBox.

Были реализованы два типа обработчиков:

### 1. Программный подход (через код)
Для первой кнопки создан слушатель View.OnClickListener прямо внутри метода onCreate. При клике выводится мой номер по журналу, а чекбокс переводится в активное состояние (setChecked(true)).

### 2. Декларативный подход (через XML)
Для второй кнопки в файле разметки прописан атрибут android:onClick="onMyButtonClick". Сам метод реализован в классе MainActivity. При клике чекбокс снимается, текст очищается и вызывается всплывающее системное уведомление Toast.

**Код `MainActivity.java`:**
```java
package com.mirea.Samsonova.buttonclicker;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private TextView textViewStudent;
    private Button btnWhoAmI;
    private Button btnItIsNotMe;
    private CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        textViewStudent = findViewById(R.id.tvOut);
        btnWhoAmI = findViewById(R.id.btnWhoAmI);
        btnItIsNotMe = findViewById(R.id.btnItIsNotMe);
        checkBox = findViewById(R.id.checkBox);

        View.OnClickListener oclBtnWhoAmI = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewStudent.setText("Мой номер по списку № 19");
                checkBox.setChecked(true);
            }
        };
        btnWhoAmI.setOnClickListener(oclBtnWhoAmI);
    }

    public void onMyButtonClick(View view) {
        textViewStudent.setText("Это не я сделал");
        checkBox.setChecked(false);
        Toast.makeText(this, "Ещё один способ!", Toast.LENGTH_SHORT).show();
    }
}
```
---

## 4. Результаты работы 
Практическая работа выполнена в полном объеме. В процессе разработки:
* Закреплены навыки проектирования интерфейсов от базовых таблиц и рядов до сложного позиционирования элементов с помощью `ConstraintLayout`.
* Изучена система папок и ресурсов Android, успешно протестировано поведение приложения при смене ориентации экрана.
* Получен практический опыт программирования интерфейса на Java: поиск элементов по ID с помощью `findViewById` и маршрутизация событий нажатия различными методами (через Listeners и атрибуты XML). Приложение корректно реагирует на все действия пользователя.
