package com.perry.biblioteca.usecases

abstract class UCMono<R, T> {
    abstract fun apply(t: T) : R;
}