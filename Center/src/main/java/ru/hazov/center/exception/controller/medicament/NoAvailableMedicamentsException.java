package ru.hazov.center.exception.controller.medicament;

import ru.hazov.center.exception.controller.ControllerException;

public class NoAvailableMedicamentsException extends ControllerException {
    private static final String message = "Нет доступных медикаментов";

    public NoAvailableMedicamentsException() {
        super(message);
    }
    public NoAvailableMedicamentsException(String message) {
        super(message);
    }
}
