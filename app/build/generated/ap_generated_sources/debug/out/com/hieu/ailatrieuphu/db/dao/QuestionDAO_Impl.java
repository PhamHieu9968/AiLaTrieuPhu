package com.hieu.ailatrieuphu.db.dao;

import android.database.Cursor;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import com.hieu.ailatrieuphu.db.entites.Question;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class QuestionDAO_Impl implements QuestionDAO {
  private final RoomDatabase __db;

  public QuestionDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
  }

  @Override
  public List<Question> getAllQuestion() {
    final String _sql = "SELECT*FROM(SELECT*FROM question WHERE level = 1 ORDER by random() LIMIT 1)\n"
            + "UNION SELECT*FROM(SELECT*FROM question WHERE level = 2 ORDER by random() LIMIT 1)\n"
            + "UNION SELECT*FROM(SELECT*FROM question WHERE level = 3 ORDER by random() LIMIT 1)\n"
            + "UNION SELECT*FROM(SELECT*FROM question WHERE level = 4 ORDER by random() LIMIT 1)\n"
            + "UNION SELECT*FROM(SELECT*FROM question WHERE level = 5 ORDER by random() LIMIT 1)\n"
            + "UNION SELECT*FROM(SELECT*FROM question WHERE level = 6 ORDER by random() LIMIT 1)\n"
            + "UNION SELECT*FROM(SELECT*FROM question WHERE level = 7 ORDER by random() LIMIT 1)\n"
            + "UNION SELECT*FROM(SELECT*FROM question WHERE level = 8 ORDER by random() LIMIT 1)\n"
            + "UNION SELECT*FROM(SELECT*FROM question WHERE level = 9 ORDER by random() LIMIT 1)\n"
            + "UNION SELECT*FROM(SELECT*FROM question WHERE level = 10 ORDER by random() LIMIT 1)\n"
            + "UNION SELECT*FROM(SELECT*FROM question WHERE level = 11 ORDER by random() LIMIT 1)\n"
            + "UNION SELECT*FROM(SELECT*FROM question WHERE level = 12 ORDER by random() LIMIT 1)\n"
            + "UNION SELECT*FROM(SELECT*FROM question WHERE level = 13 ORDER by random() LIMIT 1)\n"
            + "UNION SELECT*FROM(SELECT*FROM question WHERE level = 14 ORDER by random() LIMIT 1)\n"
            + "UNION SELECT*FROM(SELECT*FROM question WHERE level = 15 ORDER by random() LIMIT 1)\n"
            + "ORDER BY level ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "_id");
      final int _cursorIndexOfQuestion = CursorUtil.getColumnIndexOrThrow(_cursor, "question");
      final int _cursorIndexOfLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "level");
      final int _cursorIndexOfCaseA = CursorUtil.getColumnIndexOrThrow(_cursor, "casea");
      final int _cursorIndexOfCaseB = CursorUtil.getColumnIndexOrThrow(_cursor, "caseb");
      final int _cursorIndexOfCaseC = CursorUtil.getColumnIndexOrThrow(_cursor, "casec");
      final int _cursorIndexOfCaseD = CursorUtil.getColumnIndexOrThrow(_cursor, "cased");
      final int _cursorIndexOfTrueCase = CursorUtil.getColumnIndexOrThrow(_cursor, "truecase");
      final List<Question> _result = new ArrayList<Question>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Question _item;
        _item = new Question();
        _item.id = _cursor.getInt(_cursorIndexOfId);
        if (_cursor.isNull(_cursorIndexOfQuestion)) {
          _item.question = null;
        } else {
          _item.question = _cursor.getString(_cursorIndexOfQuestion);
        }
        _item.level = _cursor.getInt(_cursorIndexOfLevel);
        if (_cursor.isNull(_cursorIndexOfCaseA)) {
          _item.caseA = null;
        } else {
          _item.caseA = _cursor.getString(_cursorIndexOfCaseA);
        }
        if (_cursor.isNull(_cursorIndexOfCaseB)) {
          _item.caseB = null;
        } else {
          _item.caseB = _cursor.getString(_cursorIndexOfCaseB);
        }
        if (_cursor.isNull(_cursorIndexOfCaseC)) {
          _item.caseC = null;
        } else {
          _item.caseC = _cursor.getString(_cursorIndexOfCaseC);
        }
        if (_cursor.isNull(_cursorIndexOfCaseD)) {
          _item.caseD = null;
        } else {
          _item.caseD = _cursor.getString(_cursorIndexOfCaseD);
        }
        if (_cursor.isNull(_cursorIndexOfTrueCase)) {
          _item.trueCase = null;
        } else {
          _item.trueCase = _cursor.getString(_cursorIndexOfTrueCase);
        }
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Question getQuestionByLevel(final String level) {
    final String _sql = "SELECT * FROM Question WHERE level = ? ORDER BY RANDOM() LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (level == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, level);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "_id");
      final int _cursorIndexOfQuestion = CursorUtil.getColumnIndexOrThrow(_cursor, "question");
      final int _cursorIndexOfLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "level");
      final int _cursorIndexOfCaseA = CursorUtil.getColumnIndexOrThrow(_cursor, "casea");
      final int _cursorIndexOfCaseB = CursorUtil.getColumnIndexOrThrow(_cursor, "caseb");
      final int _cursorIndexOfCaseC = CursorUtil.getColumnIndexOrThrow(_cursor, "casec");
      final int _cursorIndexOfCaseD = CursorUtil.getColumnIndexOrThrow(_cursor, "cased");
      final int _cursorIndexOfTrueCase = CursorUtil.getColumnIndexOrThrow(_cursor, "truecase");
      final Question _result;
      if(_cursor.moveToFirst()) {
        _result = new Question();
        _result.id = _cursor.getInt(_cursorIndexOfId);
        if (_cursor.isNull(_cursorIndexOfQuestion)) {
          _result.question = null;
        } else {
          _result.question = _cursor.getString(_cursorIndexOfQuestion);
        }
        _result.level = _cursor.getInt(_cursorIndexOfLevel);
        if (_cursor.isNull(_cursorIndexOfCaseA)) {
          _result.caseA = null;
        } else {
          _result.caseA = _cursor.getString(_cursorIndexOfCaseA);
        }
        if (_cursor.isNull(_cursorIndexOfCaseB)) {
          _result.caseB = null;
        } else {
          _result.caseB = _cursor.getString(_cursorIndexOfCaseB);
        }
        if (_cursor.isNull(_cursorIndexOfCaseC)) {
          _result.caseC = null;
        } else {
          _result.caseC = _cursor.getString(_cursorIndexOfCaseC);
        }
        if (_cursor.isNull(_cursorIndexOfCaseD)) {
          _result.caseD = null;
        } else {
          _result.caseD = _cursor.getString(_cursorIndexOfCaseD);
        }
        if (_cursor.isNull(_cursorIndexOfTrueCase)) {
          _result.trueCase = null;
        } else {
          _result.trueCase = _cursor.getString(_cursorIndexOfTrueCase);
        }
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
