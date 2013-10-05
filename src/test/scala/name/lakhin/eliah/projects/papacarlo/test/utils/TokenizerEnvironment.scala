/*
   Copyright 2013 Ilya Lakhin (Илья Александрович Лахин)

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
package name.lakhin.eliah.projects
package papacarlo.test.utils

import name.lakhin.eliah.projects.papacarlo.Lexer

final class TokenizerEnvironment(lexerConstructor: () => Lexer)
  extends Environment(lexerConstructor) {

  def getResult = {
    var context = ""
    val result = new StringBuilder

    for (token <- lexer.fragments.rootFragment.getTokens) {
      token.getContext.view
      if (token.isMutable) result ++= "`"

      if (token.isSkippable) result ++= token.value
      else result ++= token.kind

      if (token.isMutable) result ++= "`"

      val tokenContext = token.getContext.view
      if (context != tokenContext) {
        context = tokenContext
        result ++= "~" + context + "~"
      }
    }

    result.toString()
  }

  def prepare() {}
}