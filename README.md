# `add_lang`

Some `ifo` files don't have an appropriate bookname. The bookname only contains
the word `abbrev`.
This is not enough, because dictionary clients won't show useful info for these files
in this case.
The bookname should have a better content, something like `abbrev En-En`.

This program does the following:
1. finds files by text mask (`*.ifo`)
2. checks the content of the file (specifically - the bookname)
3. changes the bookname by adding a language or the whole filename to it
4. writes the new content to a new file (`*.ifo.new`).

E.g.:

the file `AllEnEn_abrv.ifo` has the following content:
```
wordcount=19
idxfilesize=1234
bookname=Abbrev
date=2022.01.27
```

After the change, the new file `AllEnEn_abrv.ifo.new` has the following content:
```
wordcount=19
idxfilesize=1234
bookname=Abbrev AllEnEn
date=2022.01.27
```

## Usage

`lein run -- -i <INPUT_DIRECTORY>`

## License

Copyright © 2022 FIXME

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
