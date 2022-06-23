package LocalDB

interface LocalRepository {

    suspend  fun ReadAllData():List<Question>
    suspend fun InsertOrUPdate(question:Question)
    suspend fun Delete(question:Question)
    suspend fun DeleteAll()

}