import os

def runCmd(cmd):
    ret = os.system(cmd)
    if ret:
        msg = r'this command got error {ret}: {cmd}'
        print(msg)

def setupStudentDir(studentDir):
    cmd = f'rm -rf {studentDir}'
    runCmd(cmd)
    os.mkdir(studentDir)

def lineMatchesStudentCodeBegin(line):
    return 'STUDENT CODE BEGIN' in line

def lineMatchesStudentCodeAlt(line):
    return 'STUDENT CODE ALT' in line

def lineMatchesStudentCodeEnd(line):
    return 'STUDENT CODE END' in line

def uncommentAltLine(line):
    return line.replace('// ', '', 1)

def commentToStartAlt(line):
    locate = line.find('//') + 3
    prefix = line[:locate]
    return f"\n{prefix}This code is just temporary and should be replaced:\n"
    
def commentStateMachine(lines):
    output = []
    state = 'normal'
    for line in lines:
        if state == 'normal':
            output.append(line)
            if lineMatchesStudentCodeBegin(line):
                state = 'student'
        elif lineMatchesStudentCodeEnd(line):
            output.append("\n")
            output.append(line)
            state = 'normal'
        elif state == 'student':
            if lineMatchesStudentCodeAlt(line):
                line = commentToStartAlt(line)
                output.append(line)
                state = 'alt'
        else: # note that: state == 'alt' and it is not the END marker
            line = uncommentAltLine(line)
            output.append(line)
    return output

def writeNamesFile(names, count, studentDir):
    line   = f"{count}\n"
    prefix = [line] + names[:count]
    fName  = f'{studentDir}/names{count}.txt'
    with open(fName, 'w') as f:
        f.writelines(prefix)
    
def writeAlternateNames(bothFile, studentDir):
    with open(bothFile) as f:
        lines = f.readlines()
    names = []
    column = 0
    for line in lines:
        cols  = line.split()
        name   = cols[column]
        column = 1-column
        name  += "\n"
        names.append(name)
    counts = [0,1,2,3,5,7,11,18,26]
    for count in counts:
        writeNamesFile(names, count, studentDir)
    
def writeNames(studentDir):
    writeAlternateNames('names-both-male-female.txt',studentDir)

def makeStudentVersion(solutionFile, studentDir):
    with open(solutionFile) as f:
        lines = f.readlines()
    studentVersion = commentStateMachine(lines)
    studentFile = f'{studentDir}/{solutionFile}'
    with open(studentFile, 'w') as f:
        f.writelines(studentVersion)
    
def main():
    studentDir = 'ZoomRooms'
    setupStudentDir(studentDir)
    solutionFile = 'ZoomRooms.java'
    makeStudentVersion(solutionFile, studentDir)
    writeNames(studentDir)

main()