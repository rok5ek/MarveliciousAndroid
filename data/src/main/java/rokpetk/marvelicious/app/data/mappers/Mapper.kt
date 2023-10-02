package rokpetk.marvelicious.app.data.mappers

interface Mapper<in From, out To> {
    fun From.mapTo(): To
}
