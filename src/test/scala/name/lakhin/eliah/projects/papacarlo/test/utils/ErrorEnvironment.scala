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

import name.lakhin.eliah.projects.papacarlo.{Lexer, Syntax}

final class ErrorEnvironment(lexerConstructor: () => Lexer,
                             syntaxConstructor: Lexer => Syntax)
  extends SyntaxEnvironment(lexerConstructor, syntaxConstructor) {

  def getResult = syntax
    .getErrors
    .map(error => " > " + error.description +
      (
        if (shortOutput) " " + lexer.rangeToString(error.range)
        else ":\n" + lexer.highlight(error.range, Some(10)))
      )
    .mkString("\n\n")

  def prepare() {}
}