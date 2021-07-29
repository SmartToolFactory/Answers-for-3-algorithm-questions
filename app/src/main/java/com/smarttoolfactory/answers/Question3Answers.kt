package com.smarttoolfactory.answers

fun main() {

//  val parsedStringRegex = parseString("    ##    This is a test       ")
//    val parsedStringRegex = parseStringWithRegex("########## Too Many")
//  val parsedStringRegex = parseString("Test")
//    println("Parsed String: $parsedString")

//    val parsedStringAssisted = parseStringAssisted(" ####    This is a test       ")
//    println("Parsed Assisted: $parsedStringAssisted")

    val parsedString = parseString("  ## This is a test       ")
    println("Parsed : $parsedString")

}

/**
 * Parses a String to tokenize from *#* chars to ```<h></h>``` using ***Regular Expressions***
 */
fun parseStringWithRegex(source: String): String {

    val regex = REGEX.toRegex()
    val find: MatchResult? = regex.find(source)

    return find?.let { matchResult ->

        val results = matchResult.groupValues
        val headerLevel = results[0].filter { it == '#' }.count()
        "<h$headerLevel>${(results[2] ?: "").trim()}</h$headerLevel>"

    } ?: "Invalid String"
}

/**
 * This function uses Kotlin based ready functions to find index and number of header level
 */
fun parseStringAssisted(source: String): String {

    val isValidHeader = source.contains("# ")

    if (!isValidHeader) return "Invalid String, Missing Space or Headers!"

    val indexOfEndOfHeader = source.indexOf("# ") + 1

    val headerLevel =
        source.dropLast(source.length - indexOfEndOfHeader).filter { it == '#' }.count()

    if (headerLevel > 6) return "Invalid String, Too many hashes!"

    val title = source.drop(indexOfEndOfHeader)

    val result = "<h$headerLevel>${(title).trim()}</h$headerLevel>"
    println(result)

    return result
}

/**
 * This function uses boolean, for loop and indexes to determine if [source] can be parsed
 * into a valid header String.
 */
fun parseString(source: String): String {

    var firstIndexOfHeader = -1
    var lastIndexOfHeader = -1

    var firstIndexOfTitle = -1

    var headerStarted = false
    var headerEnd = false
    var titleStarted = false
    var spaceAfterHeader = false

    var headerLevel = 0

    source.forEachIndexed { index, c ->

        if (c == '#') {

            // This is the first hash of header, it must be before a title
            if (!headerStarted && !spaceAfterHeader) {
                firstIndexOfHeader = index
                headerStarted = true
            }

            // Count number of header level
            if (headerStarted && !headerEnd) {
                headerLevel++

                if (headerLevel > 6) return "Invalid String, too many hashes!"
            }

        } else if (c == ' ') {
            // this is first space after header
            if (headerStarted && !headerEnd && firstIndexOfHeader + headerLevel == index) {
                headerEnd = true
                spaceAfterHeader = true
                lastIndexOfHeader = index
            }
        } else {
            if (headerStarted && headerEnd && !titleStarted) {
                firstIndexOfTitle = index
            }
            titleStarted = true
        }
    }

    if (headerLevel ==0) return "Invalid String, no headers!"
    if (!spaceAfterHeader) return "Invalid String, Missing Space!"

    val title = source.substring(firstIndexOfTitle)
    return "<h$headerLevel>${title.trim()}</h$headerLevel>"
}


const val REGEX = "^(\\s*#{1,6}\\s+)((\\w*\\s*)+)"