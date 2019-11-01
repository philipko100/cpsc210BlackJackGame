package game;

import java.util.Objects;

public class Times {
    public int number = 0;

    public Times(int number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Times times = (Times) o;
        return number == times.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

}
