import os
import shutil
import sys

NAMES_FILE = "names-both-male-female.txt"
CODE_FILES = ["ZoomRooms.java"]

def setup_student_dir(student_dir):
    shutil.rmtree(student_dir, ignore_errors=True)
    os.mkdir(student_dir)

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

def write_names_file(names, count, student_dir):
    line   = f"{count}\n"
    prefix = [line] + names[:count]
    filename  = os.path.join(student_dir, f'names{count}.txt')
    with open(filename, 'w') as f:
        f.writelines(prefix)
    
def write_alternate_names(bothFile, studentDir):
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
        write_names_file(names, count, studentDir)
    
def write_names(student_dir, source_dir):
    write_alternate_names(os.path.join(source_dir, NAMES_FILE), student_dir)

def make_student_version(student_dir, solution_files):
    for solution_file in solution_files:
        with open(solution_file) as f:
            solution_lines = f.readlines()
        student_lines = commentStateMachine(solution_lines)
        student_file = os.path.join(student_dir, os.path.basename(solution_file))
        with open(student_file, 'w') as f:
            f.writelines(student_lines)

def main():
    if len(sys.argv) < 4:
        print(
            "make-student-zip <student folder> <source folder> <solution file 1> [<solution file 2> ...]",
            file=sys.stderr,
        )
        return
    
    student_dir = sys.argv[1]
    source_dir = sys.argv[2]
    solution_files = list(map(lambda filename: os.path.join(source_dir, filename), sys.argv[3:]))

    setup_student_dir(student_dir)
    make_student_version(student_dir, solution_files)
    write_names(student_dir, source_dir)

main()
