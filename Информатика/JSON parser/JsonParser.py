import re


def parsing(txt, idx):
    data = {}
    while True:
        if idx >= len(txt) or "}" in txt[idx]:
            break
        elif "{" in txt[idx]:
            temp_data, temp_idx = parsing(txt, idx + 1)
            data[txt[idx - 1]] = temp_data
            idx = temp_idx
        elif ": " in txt[idx]:
            data[txt[idx - 1]] = txt[idx + 1]
        idx += 1
    return data, idx


def sealing(data, file, lvl):
    file.write("<xml>\n")
    for key in data:
        file.write("\t" * lvl + "<{}>".format(key))
        if isinstance(data[key], dict):
            file.write("\n")
            sealing(data[key], file, lvl + 1)
            file.write("\t" * lvl + "</{}>\n".format(key))
        else:
            file.write(data[key])
            file.write("</{}>\n".format(key))
    file.write("</xml>")
    return 0


def re_parsing(txt, idx):
    data = {}
    while True:
        if idx >= len(txt) or re.match(r"(.*}+.*)+", txt[idx]):
            break
        elif re.match(r"(.*{+.*)+", txt[idx]):
            temp_data, temp_idx = parsing(txt, idx + 1)
            data[txt[idx - 1]] = temp_data
            idx = temp_idx
        elif re.match(r"(\s*:+\s*)+", txt[idx]):
            data[txt[idx - 1]] = txt[idx + 1]
        idx += 1
    return data, idx
