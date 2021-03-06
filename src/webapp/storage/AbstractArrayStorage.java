package webapp.storage;

import webapp.exception.StorageException;
import webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {
    protected static final int STORAGE_LENGTH = 10_000;
    protected final Resume[] storage = new Resume[STORAGE_LENGTH];
    protected int size = 0;

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    protected void doSave(Resume resume, Integer searchKey) {
      if (size == STORAGE_LENGTH) {
          throw new StorageException("Storage overflow", resume.getId());
      } else {
          insertElement(resume,searchKey);
          size++;
      }
    }

    @Override
    protected void doUpdate(Resume resume, Integer searchKey) {
        storage[searchKey] = resume;
    }

    @Override
    protected void doDelete(Integer searchKey) {
        fillDeletedElement(searchKey);
        storage[size - 1] = null;
        size--;
    }

    @Override
    public List<Resume> doCopyAll() {
        return Arrays.asList(Arrays.copyOfRange(storage, 0 , size));
    }

    public Resume doGet(Integer searchKey) {
        return storage[searchKey];
    }

    @Override
    protected boolean isExist(Integer searchKey) {
        return searchKey >= 0;
    }

    protected abstract Integer getSearchKey(String id);
    protected abstract void insertElement(Resume resume, int index);
    protected abstract void fillDeletedElement(int index);
}
