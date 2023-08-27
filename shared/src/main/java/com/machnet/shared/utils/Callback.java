package com.machnet.shared.utils;

import java.util.List;

public interface Callback<T> {

    void call(List<T> objects);
}
