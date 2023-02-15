package com.paragon.uberdeluxe.data.models;

public enum Rating {
    BAD(1),
    GOOD(2),
    SATISFIED(3),
    EXCELLENT(4);
    private int rating;
    Rating(int value) {
        this.rating = rating;
    }

    public int getRating() {
        return rating;
    }
}
