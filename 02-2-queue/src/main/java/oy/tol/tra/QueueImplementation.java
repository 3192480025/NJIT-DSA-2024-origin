package oy.tol.tra;

public class QueueImplementation<E> implements QueueInterface<E> {

    private Object[] itemArray;
    private int capacity;
    private int front;
    private int rear;
    private int size = 0;
    private static final int DEFAULT_QUEUE_SIZE = 10;

    public QueueImplementation() throws QueueAllocationException {
        this(DEFAULT_QUEUE_SIZE);
    }

    public QueueImplementation(int capacity) throws QueueAllocationException {
        if (capacity < 2) {
            throw new QueueAllocationException("Capacity must be at least 2.");
        }

        try {
            itemArray = new Object[capacity];
            this.capacity = capacity;
            front = 0;
            rear = -1;
        } catch (Exception e) {
            throw new QueueAllocationException("Failed to allocate queue memory.");
        }
    }

    @Override
    public int capacity() {
        return capacity;
    }

    @Override
    public void enqueue(E element) throws QueueAllocationException, NullPointerException {
        if (element == null) {
            throw new NullPointerException("Cannot enqueue null element.");
        }

        if (size() >= capacity) {
            // If the queue is full, double its capacity.
            int newCapacity = capacity * 2;
            try {
                Object[] newArray = new Object[newCapacity];
                int currentSize = size();
                for (int i = 0; i < currentSize; i++) {
                    newArray[i] = itemArray[(front + i) % capacity];
                }
                itemArray = newArray;
                capacity = newCapacity;
                front = 0;
                rear = currentSize - 1;

            } catch (Exception e) {
                throw new QueueAllocationException("Failed to reallocate queue memory.");
            }
        }

        rear = (rear + 1) % capacity;
        itemArray[rear] = element;
        size++;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E dequeue() throws QueueIsEmptyException {
        if (isEmpty()) {
            throw new QueueIsEmptyException("Cannot dequeue from an empty queue.");
        }

        E dequeuedElement = (E) itemArray[front];
        itemArray[front] = null;
        front = (front + 1) % capacity;
        size--;

        return dequeuedElement;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E element() throws QueueIsEmptyException {
        if (isEmpty()) {
            throw new QueueIsEmptyException("Cannot access element from an empty queue.");
        }

        return (E) itemArray[front];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        front = 0;
        rear = -1;
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        if (!isEmpty()) {
            int index = front;
            do {
                builder.append(itemArray[index].toString());
                if (index != rear) {
                    builder.append(", ");
                }
                index = (index + 1) % capacity;
            } while (index != (rear + 1) % capacity);
        }
        builder.append("]");
        return builder.toString();
    }
}
