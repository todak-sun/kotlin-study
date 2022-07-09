package io.study.kotlininaction._04

class Client(val name: String, val postalCode: Int) {
    override fun toString() = "Client(name=$name, postalCode=$postalCode)"

    override fun equals(other: Any?): Boolean {
        if (other == null || other !is Client) return false // 코틀린의 is 검사는 자바의 instanceof와 같다.
        return name == other.name && postalCode == other.postalCode
    }

    override fun hashCode(): Int = name.hashCode() * 31 + postalCode
}

data class DataClient(val name: String, val postalCode: Int)
// data 클래스는 equals, hashcode, toString을 모두  만들어준다.

// 무저ㅣ

fun main() {
    val client1 = Client("Todak", 123)
    val client2 = Client("Todak", 123)
    println("client : $client1")
    println("client1 == client2 : ${client1 == client2}")

    val processed = hashSetOf(Client("선용주", 1234))
    println("""processed.contains(Client("선용주", 1234)) : ${processed.contains(Client("선용주", 1234))}""")

    val dataClient = DataClient("name", 1234)
    val copiedDataClient = dataClient.copy()
    println("dataClient == copiedDataClient ${dataClient == copiedDataClient}")



}


