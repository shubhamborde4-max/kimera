package com.example.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.model.ConsultationSession
import kotlinx.coroutines.flow.Flow

@Dao
interface SessionDao {
    @Query("SELECT * FROM consultation_sessions ORDER BY timestamp DESC")
    fun getAllSessions(): Flow<List<ConsultationSession>>

    @Query("SELECT * FROM consultation_sessions WHERE id = :sessionId LIMIT 1")
    suspend fun getSessionById(sessionId: String): ConsultationSession?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSession(session: ConsultationSession)

    @Update
    suspend fun updateSession(session: ConsultationSession)

    @Query("SELECT COUNT(*) FROM consultation_sessions")
    fun getTotalSessionsCount(): Flow<Int>

    @Query("SELECT COUNT(*) FROM consultation_sessions WHERE consentRenderGranted = 1")
    fun getConsentGrantedCount(): Flow<Int>

    @Query("SELECT COUNT(*) FROM consultation_sessions WHERE outcomeMatch = 'Exact Match'")
    fun getExactMatchCount(): Flow<Int>
}
