package blarb;

/**
 * {@code blarb.Task} contains an action and its completion status.
 */
class Task {
    private final String description;
    private boolean isDone;

    /**
     * Constructs a new uncompleted  {@code blarb.Task}.
     *
     * @param description The name of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    private String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Toggles to completion status to completed.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Converts {@code blarb.Task} into string format to be stored in file.
     *
     * @return String format to be stored in file.
     */
    public String encode() {
        return String.format("%d / %s", isDone ? 1 : 0, description);
    }

    /**
     * String representation of the blarb.Task.
     *
     * @return blarb.Task in check list form.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }
}