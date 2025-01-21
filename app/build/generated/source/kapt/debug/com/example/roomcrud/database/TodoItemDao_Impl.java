package com.example.roomcrud.database;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.EntityUpsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@SuppressWarnings({"unchecked", "deprecation"})
public final class TodoItemDao_Impl implements TodoItemDao {
  private final RoomDatabase __db;

  private final EntityDeletionOrUpdateAdapter<TodoItem> __deletionAdapterOfTodoItem;

  private final EntityUpsertionAdapter<TodoItem> __upsertionAdapterOfTodoItem;

  public TodoItemDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__deletionAdapterOfTodoItem = new EntityDeletionOrUpdateAdapter<TodoItem>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `TodoItem` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @Nullable final TodoItem entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__upsertionAdapterOfTodoItem = new EntityUpsertionAdapter<TodoItem>(new EntityInsertionAdapter<TodoItem>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT INTO `TodoItem` (`title`,`description`,`dateAdded`,`id`) VALUES (?,?,?,nullif(?, 0))";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @Nullable final TodoItem entity) {
        if (entity.getTitle() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getTitle());
        }
        if (entity.getDescription() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getDescription());
        }
        statement.bindLong(3, entity.getDateAdded());
        statement.bindLong(4, entity.getId());
      }
    }, new EntityDeletionOrUpdateAdapter<TodoItem>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE `TodoItem` SET `title` = ?,`description` = ?,`dateAdded` = ?,`id` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @Nullable final TodoItem entity) {
        if (entity.getTitle() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getTitle());
        }
        if (entity.getDescription() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getDescription());
        }
        statement.bindLong(3, entity.getDateAdded());
        statement.bindLong(4, entity.getId());
        statement.bindLong(5, entity.getId());
      }
    });
  }

  @Override
  public Object deleteNote(final TodoItem todoItem, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfTodoItem.handle(todoItem);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object upsertNote(final TodoItem todoItem, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __upsertionAdapterOfTodoItem.upsert(todoItem);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<TodoItem>> getNotesOrderdByDateAdded() {
    final String _sql = "SELECT * FROM todoItem ORDER BY dateAdded";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"todoItem"}, new Callable<List<TodoItem>>() {
      @Override
      @NonNull
      public List<TodoItem> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfDateAdded = CursorUtil.getColumnIndexOrThrow(_cursor, "dateAdded");
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final List<TodoItem> _result = new ArrayList<TodoItem>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final TodoItem _item;
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final long _tmpDateAdded;
            _tmpDateAdded = _cursor.getLong(_cursorIndexOfDateAdded);
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            _item = new TodoItem(_tmpTitle,_tmpDescription,_tmpDateAdded,_tmpId);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<TodoItem>> getNotesOrderdByTitle() {
    final String _sql = "SELECT * FROM todoItem ORDER BY title ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"todoItem"}, new Callable<List<TodoItem>>() {
      @Override
      @NonNull
      public List<TodoItem> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfDateAdded = CursorUtil.getColumnIndexOrThrow(_cursor, "dateAdded");
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final List<TodoItem> _result = new ArrayList<TodoItem>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final TodoItem _item;
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final long _tmpDateAdded;
            _tmpDateAdded = _cursor.getLong(_cursorIndexOfDateAdded);
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            _item = new TodoItem(_tmpTitle,_tmpDescription,_tmpDateAdded,_tmpId);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
