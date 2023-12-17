package ru.sberbank.edu;

import java.util.Collection;

public class CustomArrayImpl implements CustomArray {

   private static final int DEFAULT_CAPACITY = 10;
   private static final int GROW_COEFFICIENT = 2;
   private Object[] array = new Object[DEFAULT_CAPACITY];
   private int capacity = DEFAULT_CAPACITY;
   private int size = 0;

    /**
     * Заполненность массива
     */
    @Override
    public int size() {
       return this.size;
    }

    /**
     * Массив пустой ?
     *
     */
    @Override
    public boolean isEmpty() {
       return this.size == 0;
    }

    /**
     * Добавить элемент в массив
     */
    @Override
    public boolean add(Object item) {
       this.array[size] = item;
       this.size++;
       // Нужно увеличить размер массива
        if ( this.size == this.capacity ) grow();
        return true;
    }

    /**
     *  Добавить массив элементов
     * @param items
     * @throws IllegalArgumentException if parameter items is null
     */
    @Override
    public boolean addAll(Object[] items) {
       if ( items == null ) {
          throw new IllegalArgumentException("Пустой массив");
       }
        for (Object el : items) add(el);
        return true;
    }

    /**
     * Add all items.
     *
     * @param items
     * @throws IllegalArgumentException if parameter items is null
     */
    @Override
    public boolean addAll(Collection items) {
       if ( items == null ) {
          throw new IllegalArgumentException("Пустой массив");
       }
       for (Object el : items) add(el);
       return true;
    }

    /**
     * Add items to current place in array.
     *
     * @param index - index
     * @param items - items for insert
     * @throws ArrayIndexOutOfBoundsException if index is out of bounds
     * @throws IllegalArgumentException       if parameter items is null
     */
    @Override
    public boolean addAll(int index, Object[] items) {

       int counter =  0;

       if ( items == null ) {
          throw new IllegalArgumentException("Пустой массив");
       }
       else if ( index < 0 || this.capacity - 1 < index ) {
          throw new IndexOutOfBoundsException();
       };

       while( this.capacity < this.size + items.length ) grow();

       for ( int i = index; i < index + items.length; i++ ) {
           int newIndex = index + items.length + counter;
          if ( this.array[i] ==  null ) {
              this.array[i] = items[counter];
          } else {
             this.array[newIndex] = this.array[i];
             this.array[i] = items[counter];
          }
          counter++;
       };

       return true;
    }

    /**
     * Get item by index.
     *
     * @param index - index
     * @throws ArrayIndexOutOfBoundsException if index is out of bounds
     */
    @Override
    public Object get(int index) {
       if (this.size > index) {
          return this.array[index];
       } else {
          throw new ArrayIndexOutOfBoundsException();
       }
    }

    /**
     * Set item by index.
     *
     * @param index - index
     * @param item
     * @return old value
     * @throws ArrayIndexOutOfBoundsException if index is out of bounds
     */
    @Override
    public Object set(int index, Object item) {
       if (this.size > index) {
          Object old = this.array[index];
          this.array[index] = item;
          return old;
       } else {
          throw new ArrayIndexOutOfBoundsException();
       }
    }

    /**
     * Remove item by index.
     *
     * @param index - index
     * @throws ArrayIndexOutOfBoundsException if index is out of bounds
     */
    @Override
    public void remove(int index) {
       if (this.size > index) {
          for (int i = index; i < this.size; i++) {
             this.array[i] = this.array[i + 1];
          }
       } else {
          throw new ArrayIndexOutOfBoundsException();
       }
    }

    /**
     * Remove item by value. Remove first item occurrence.
     *
     * @param item - item
     * @return true if item was removed
     */
    @Override
    public boolean remove(Object item) {
       for (int i = 0; i < this.size; i++) {
          if (this.array[i].equals(item)) {
             remove(i);
             return true;
          }
       }
       return false;
    }

    /**
     * Checks if item exists.
     *
     * @param item - item
     * @return true or false
     */
    @Override
    public boolean contains(Object item) {
       for (int i = 0; i < this.capacity; i++) {
          if (this.array[i] == null) continue;
          else if (this.array[i].equals(item)){
             return true;
          }
       }
       return false;
    }

    /**
     * Index of item.
     *
     * @param item - item
     * @return index of element or -1 of list doesn't contain element
     */
    @Override
    public int indexOf(Object item) {
       for (int i = 0; i < this.capacity; i++) {
           if (this.array[i] == null) continue;
           else if (this.array[i].equals(item)) {
             return i;
          }
       }
       return -1;
    }

    /**
     * Grow current capacity to store new elements if needed.
     *
     * @param newElementsCount - new elements count
     */
    @Override
    public void ensureCapacity(int newElementsCount) {
       while ( newElementsCount > this.capacity)grow();
    }

    /**
     * Get current capacity.
     */
    @Override
    public int getCapacity() {
        return this.capacity;
    }

    /**
     * Reverse list.
     */
    @Override
    public void reverse() {
       Object row;
       for (int i = 0; i < this.size / 2; i++) {
          row = this.array[i];
          this.array[i] = this.array[this.size - i - 1];
          this.array[this.size - i - 1] = row;
       }
    }

    /**
     * Get copy of current array.
     */
    @Override
    public Object[] toArray() {
       Object[] copyArray = new Object[this.capacity];
       for ( int i = 0; i < this.size; i++ ) copyArray[i] = this.array[i];
        return (Object[]) copyArray;
    }

    private void grow( ){
       int newCapacity = (int) ( capacity * GROW_COEFFICIENT );
       Object[] newArray = new Object[newCapacity];
       for ( int i = 0; i < this.size ;i++ ) {
          newArray[i] = array[i];
       };
       capacity = newCapacity;
       array = newArray;
    }
}
