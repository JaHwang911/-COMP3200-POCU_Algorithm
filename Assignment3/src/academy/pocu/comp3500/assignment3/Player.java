package academy.pocu.comp3500.assignment3;

import academy.pocu.comp3500.assignment3.chess.Move;
import academy.pocu.comp3500.assignment3.chess.PlayerBase;

public class Player extends PlayerBase {
    private final static int BOARD_SIZE = 8;
    private final static int MAX_DEPTH = 3;

    private static final int[][] knightMoveOffset = {
            {1, -2},
            {2, -1},
            {2, 1},
            {1, 2},
            {-1, 2},
            {-2, 1},
            {-2, -1},
            {-1, -2}
    };

    private static final int[][] kingMoveOffset = {
            {0, -1},
            {1, -1},
            {1, 0},
            {1, 1},
            {0, 1},
            {-1, 1},
            {-1, 0},
            {-1, -1}
    };

    private final Move currentMove;

    public Player(final boolean isWhite, int maxMoveTimeMilliseconds) {
        super(isWhite, maxMoveTimeMilliseconds);

        this.currentMove = new Move();
    }

    @Override
    public Move getNextMove(char[][] board) {
        if (super.isWhite()) {
            this.currentMove.fromX = 3;
            this.currentMove.fromY = 6;
            this.currentMove.toX = 3;
            this.currentMove.toY = 4;
        } else {
            if (board[4][4] == 'p' || board[2][4] == 'p') {
                this.currentMove.fromX = 4;
                this.currentMove.fromY = 1;
                this.currentMove.toX = 4;
            } else {
                this.currentMove.fromX = 3;
                this.currentMove.fromY = 1;
                this.currentMove.toX = 3;
            }

            this.currentMove.toY = 3;
        }

        return this.currentMove;
    }

    @Override
    public Move getNextMove(char[][] board, final Move opponentMove) {
        getMinimax(board, 0, true, super.isWhite());

        return this.currentMove;
    }

    private int getMinimax(char[][] board, final int depth, boolean isMyTurn, boolean isWhiteTurn) {
        if (depth == MAX_DEPTH) {
            return getEvaluate(board);
        }

        int result = isMyTurn ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        for (int i = 0; i < BOARD_SIZE; ++i) {
            for (int j = 0; j < BOARD_SIZE; ++j) {
                if (board[i][j] == 0) {
                    continue;
                } else if (isWhiteTurn && Character.isUpperCase(board[i][j])) {
                    continue;
                } else if (!isWhiteTurn && Character.isLowerCase(board[i][j])) {
                    continue;
                }

                char symbol = board[i][j];
                int score;
                int toX;
                int toY;

                switch (symbol) {
                    case 'p':
                        if (i == 6) {
                            toX = j;
                            toY = i - 2;

                            if (isPawnMoveValid(board, j, i, toX, toY)) {
                                board[i][j] = 0;
                                board[toY][toX] = symbol;

                                score = getMinimax(board, depth + 1, !isMyTurn, !isWhiteTurn);

                                if (isMyTurn) {
                                    if (score > result) {
                                        result = score;

                                        this.currentMove.fromX = j;
                                        this.currentMove.fromY = i;
                                        this.currentMove.toX = toX;
                                        this.currentMove.toY = toY;
                                    }
                                } else {
                                    result = Math.min(score, result);
                                }

                                board[i][j] = symbol;
                                board[toY][toX] = 0;
                            }
                        }

                        toX = j;
                        toY = i - 1;

                        if (isPawnMoveValid(board, j, i, toX, toY)) {
                            board[i][j] = 0;
                            board[toY][toX] = symbol;

                            score = getMinimax(board, depth + 1, !isMyTurn, !isWhiteTurn);

                            if (isMyTurn) {
                                if (score > result) {
                                    result = score;

                                    this.currentMove.fromX = j;
                                    this.currentMove.fromY = i;
                                    this.currentMove.toX = toX;
                                    this.currentMove.toY = toY;
                                }
                            } else {
                                result = Math.min(score, result);
                            }

                            board[i][j] = symbol;
                            board[toY][toX] = 0;
                        }

                        toX = j - 1;
                        toY = i - 1;

                        if (isPawnMoveValid(board, j, i, toX, toY)) {
                            board[i][j] = 0;
                            board[toY][toX] = symbol;

                            score = getMinimax(board, depth + 1, !isMyTurn, !isWhiteTurn);

                            if (isMyTurn) {
                                if (score > result) {
                                    result = score;

                                    this.currentMove.fromX = j;
                                    this.currentMove.fromY = i;
                                    this.currentMove.toX = toX;
                                    this.currentMove.toY = toY;
                                }
                            } else {
                                result = Math.min(score, result);
                            }

                            board[i][j] = symbol;
                            board[toY][toX] = 0;
                        }

                        toX = j + 1;
                        toY = i - 1;

                        if (isPawnMoveValid(board, j, i, toX, toY)) {
                            board[i][j] = 0;
                            board[toY][toX] = symbol;

                            score = getMinimax(board, depth + 1, !isMyTurn, !isWhiteTurn);

                            if (isMyTurn) {
                                if (score > result) {
                                    result = score;

                                    this.currentMove.fromX = j;
                                    this.currentMove.fromY = i;
                                    this.currentMove.toX = toX;
                                    this.currentMove.toY = toY;
                                }
                            } else {
                                result = Math.min(score, result);
                            }

                            board[i][j] = symbol;
                            board[toY][toX] = 0;
                        }
                    case 'P':
                        toX = j;
                        toY = i + 2;

                        if (i == 1) {
                            if (isPawnMoveValid(board, j, i, toX, toY)) {
                                board[i][j] = 0;
                                board[toY][toX] = symbol;

                                score = getMinimax(board, depth + 1, !isMyTurn, !isWhiteTurn);

                                if (isMyTurn) {
                                    if (score > result) {
                                        result = score;

                                        this.currentMove.fromX = j;
                                        this.currentMove.fromY = i;
                                        this.currentMove.toX = toX;
                                        this.currentMove.toY = toY;
                                    }
                                } else {
                                    result = Math.min(score, result);
                                }

                                board[i][j] = symbol;
                                board[toY][toX] = 0;
                            }
                        }

                        toX = j;
                        toY = i + 1;

                        if (isPawnMoveValid(board, j, i, toX, toY)) {
                            board[i][j] = 0;
                            board[toY][toX] = symbol;

                            score = getMinimax(board, depth + 1, !isMyTurn, !isWhiteTurn);

                            if (isMyTurn) {
                                if (score > result) {
                                    result = score;

                                    this.currentMove.fromX = j;
                                    this.currentMove.fromY = i;
                                    this.currentMove.toX = toX;
                                    this.currentMove.toY = toY;
                                }
                            } else {
                                result = Math.min(score, result);
                            }

                            board[i][j] = symbol;
                            board[toY][toX] = 0;
                        }

                        toX = j - 1;
                        toY = i + 1;

                        if (isPawnMoveValid(board, j, i, toX, toY)) {
                            board[i][j] = 0;
                            board[toY][toX] = symbol;

                            score = getMinimax(board, depth + 1, !isMyTurn, !isWhiteTurn);

                            if (isMyTurn) {
                                if (score > result) {
                                    result = score;

                                    this.currentMove.fromX = j;
                                    this.currentMove.fromY = i;
                                    this.currentMove.toX = toX;
                                    this.currentMove.toY = toY;
                                }
                            } else {
                                result = Math.min(score, result);
                            }

                            board[i][j] = symbol;
                            board[toY][toX] = 0;
                        }

                        toX = j + 1;
                        toY = i + 1;

                        if (isPawnMoveValid(board, j, i, toX, toY)) {
                            board[i][j] = 0;
                            board[toY][toX] = symbol;

                            score = getMinimax(board, depth + 1, !isMyTurn, !isWhiteTurn);

                            if (isMyTurn) {
                                if (score > result) {
                                    result = score;

                                    this.currentMove.fromX = j;
                                    this.currentMove.fromY = i;
                                    this.currentMove.toX = toX;
                                    this.currentMove.toY = toY;
                                }
                            } else {
                                result = Math.min(score, result);
                            }

                            board[i][j] = symbol;
                            board[toY][toX] = 0;
                        }
                        break;
                    case 'n':
                    case 'N':
                        for (int k = 0; k < knightMoveOffset.length; ++k) {
                            toX = j + knightMoveOffset[k][0];
                            toY = i + knightMoveOffset[k][1];

                            if (isMoveValid(board, j, i, toX, toY)) {
                                board[i][j] = 0;
                                board[toY][toX] = symbol;

                                score = getMinimax(board, depth + 1, !isMyTurn, !isWhiteTurn);

                                if (isMyTurn) {
                                    if (score > result) {
                                        result = score;

                                        this.currentMove.fromX = j;
                                        this.currentMove.fromY = i;
                                        this.currentMove.toX = toX;
                                        this.currentMove.toY = toY;
                                    }
                                } else {
                                    result = Math.min(score, result);
                                }

                                board[i][j] = symbol;
                                board[toY][toX] = 0;
                            }
                        }
                        break;
                    case 'b':
                    case 'B':
                        score = bishopMove(board, j, i, depth, isMyTurn, isWhiteTurn);

                        if (isMyTurn) {
                            if (score > result) {
                                result = score;
                            }
                        } else {
                            if (score < result) {
                                result = score;
                            }
                        }
                        break;
                    case 'r':
                    case 'R':
                        score = rookMove(board, j, i, depth, isMyTurn, isWhiteTurn);

                        if (isMyTurn) {
                            result = Math.max(score, result);
                        } else {
                            result = Math.min(score, result);
                        }
                        break;
                    case 'q':
                    case 'Q':
                        score = bishopMove(board, j, i, depth, isMyTurn, isWhiteTurn);

                        if (isMyTurn) {
                            result = Math.max(score, result);
                        } else {
                            result = Math.min(score, result);
                        }

                        score = rookMove(board, j, i, depth, isMyTurn, isWhiteTurn);

                        if (isMyTurn) {
                            result = Math.max(score, result);
                        } else {
                            result = Math.min(score, result);
                        }
                        break;
                    case 'k':
                    case 'K':
                        for (int k = 0; k < kingMoveOffset.length; ++k) {
                            toX = j + kingMoveOffset[k][0];
                            toY = i + kingMoveOffset[k][1];

                            if (isMoveValid(board, j, i, toX, toY)) {
                                board[i][j] = 0;
                                board[toY][toX] = symbol;

                                score = getMinimax(board, depth + 1, !isMyTurn, !isWhiteTurn);

                                if (isMyTurn) {
                                    if (score > result) {
                                        result = score;

                                        currentMove.fromX = j;
                                        currentMove.fromY = i;
                                        currentMove.toX = toX;
                                        currentMove.toY = toY;
                                    }
                                } else {
                                    result = Math.min(score, result);
                                }

                                board[i][j] = symbol;
                                board[toY][toX] = 0;
                            }
                        }
                        break;
                    default:
                        assert (false);
                        break;
                }
            }
        }

        return result;
    }

    private int bishopMove(char[][] board, int fromX, int fromY, final int depth, boolean isMyTurn, boolean isWhiteTurn) {
        int result;
        int score;
        int offsetX = 1;
        int offsetY = 1;

        if (isMyTurn) {
            result = Integer.MIN_VALUE;
        } else {
            result = Integer.MAX_VALUE;
        }


        while (offsetX < BOARD_SIZE && offsetY < BOARD_SIZE) {
            int toX = fromX + offsetX;
            int toY = fromY + offsetY;

            if (isMoveValid(board, fromX, fromY, toX, toY)) {
                char temp = board[fromY][fromX];
                board[fromY][fromX] = 0;
                board[toY][toX] = temp;

                score = getMinimax(board, depth + 1, !isMyTurn, !isWhiteTurn);

                if (isMyTurn) {
                    if (score > result) {
                        result = score;

                        this.currentMove.fromX = fromX;
                        this.currentMove.fromY = fromY;
                        this.currentMove.toX = toX;
                        this.currentMove.toY = toY;
                    }
                } else {
                    result = Math.min(score, result);
                }

                board[fromY][fromX] = temp;
                board[toY][toX] = 0;
            } else {
                break;
            }
            ++offsetX;
            ++offsetY;
        }

        offsetX = -1;
        offsetY = -1;

        while (offsetX > -BOARD_SIZE && offsetY > -BOARD_SIZE) {
            int toX = fromX + offsetX;
            int toY = fromY + offsetY;

            if (isMoveValid(board, fromX, fromY, toX, toY)) {
                char temp = board[fromY][fromX];
                board[fromY][fromX] = 0;
                board[toY][toX] = temp;

                score = getMinimax(board, depth + 1, !isMyTurn, !isWhiteTurn);

                if (isMyTurn) {
                    if (score > result) {
                        result = score;

                        this.currentMove.fromX = fromX;
                        this.currentMove.fromY = fromY;
                        this.currentMove.toX = toX;
                        this.currentMove.toY = toY;
                    }
                } else {
                    result = Math.min(score, result);
                }

                board[fromY][fromX] = temp;
                board[toY][toX] = 0;
            } else {
                break;
            }
            --offsetX;
            --offsetY;
        }

        offsetX = 1;
        offsetY = -1;

        while (offsetX < BOARD_SIZE && offsetY > -BOARD_SIZE) {
            int toX = fromX + offsetX;
            int toY = fromY + offsetY;

            if (isMoveValid(board, fromX, fromY, toX, toY)) {
                char temp = board[fromY][fromX];
                board[fromY][fromX] = 0;
                board[toY][toX] = temp;

                score = getMinimax(board, depth + 1, !isMyTurn, !isWhiteTurn);

                if (isMyTurn) {
                    if (score > result) {
                        result = score;

                        this.currentMove.fromX = fromX;
                        this.currentMove.fromY = fromY;
                        this.currentMove.toX = toX;
                        this.currentMove.toY = toY;
                    }
                } else {
                    result = Math.min(score, result);
                }

                board[fromY][fromX] = temp;
                board[toY][toX] = 0;
            } else {
                break;
            }
            ++offsetX;
            --offsetY;
        }

        offsetX = -1;
        offsetY = 1;

        while (offsetX > -BOARD_SIZE && offsetY < BOARD_SIZE) {
            int toX = fromX + offsetX;
            int toY = fromY + offsetY;

            if (isMoveValid(board, fromX, fromY, toX, toY)) {
                char temp = board[fromY][fromX];
                board[fromY][fromX] = 0;
                board[toY][toX] = temp;

                score = getMinimax(board, depth + 1, !isMyTurn, !isWhiteTurn);

                if (isMyTurn) {
                    if (score > result) {
                        result = score;

                        this.currentMove.fromX = fromX;
                        this.currentMove.fromY = fromY;
                        this.currentMove.toX = toX;
                        this.currentMove.toY = toY;
                    }
                } else {
                    result = Math.min(score, result);
                }

                board[fromY][fromX] = temp;
                board[toY][toX] = 0;
            } else {
                break;
            }

            --offsetX;
            ++offsetY;
        }

        return result;
    }

    private int rookMove(char[][] board, int fromX, int fromY, final int depth, boolean isMyTurn, boolean isWhiteTurn) {
        int result;
        int score;
        int offsetX = 1;
        int offsetY = 0;

        if (isMyTurn) {
            result = Integer.MIN_VALUE;
        } else {
            result = Integer.MAX_VALUE;
        }

        while (offsetX < BOARD_SIZE) {
            int toX = fromX + offsetX;
            int toY = fromY + offsetY;

            if (isMoveValid(board, fromX, fromY, toX, toY)) {
                char temp = board[fromY][fromX];
                board[fromY][fromX] = 0;
                board[toY][toX] = temp;

                score = getMinimax(board, depth + 1, !isMyTurn, !isWhiteTurn);

                if (isMyTurn) {
                    if (score > result) {
                        result = score;

                        this.currentMove.fromX = fromX;
                        this.currentMove.fromY = fromY;
                        this.currentMove.toX = toX;
                        this.currentMove.toY = toY;
                    }
                } else {
                    result = Math.min(score, result);
                }

                board[fromY][fromX] = temp;
                board[toY][toX] = 0;
            } else {
                break;
            }
            ++offsetX;
        }

        offsetX = -1;

        while (offsetX > -BOARD_SIZE) {
            int toX = fromX + offsetX;
            int toY = fromY + offsetY;

            if (isMoveValid(board, fromX, fromY, toX, toY)) {
                char temp = board[fromY][fromX];
                board[fromY][fromX] = 0;
                board[toY][toX] = temp;

                score = getMinimax(board, depth + 1, !isMyTurn, !isWhiteTurn);

                if (isMyTurn) {
                    if (score > result) {
                        result = score;

                        this.currentMove.fromX = fromX;
                        this.currentMove.fromY = fromY;
                        this.currentMove.toX = toX;
                        this.currentMove.toY = toY;
                    }
                } else {
                    result = Math.min(score, result);
                }

                board[fromY][fromX] = temp;
                board[toY][toX] = 0;
            } else {
                break;
            }
            --offsetX;
        }

        offsetX = 0;
        offsetY = 1;

        while (offsetY < BOARD_SIZE) {
            int toX = fromX + offsetX;
            int toY = fromY + offsetY;

            if (isMoveValid(board, fromX, fromY, toX, toY)) {
                char temp = board[fromY][fromX];
                board[fromY][fromX] = 0;
                board[toY][toX] = temp;

                score = getMinimax(board, depth + 1, !isMyTurn, !isWhiteTurn);

                if (isMyTurn) {
                    if (score > result) {
                        result = score;

                        this.currentMove.fromX = fromX;
                        this.currentMove.fromY = fromY;
                        this.currentMove.toX = toX;
                        this.currentMove.toY = toY;
                    }
                } else {
                    result = Math.min(score, result);
                }

                board[fromY][fromX] = temp;
                board[toY][toX] = 0;
            } else {
                break;
            }
            ++offsetY;
        }

        offsetY = -1;

        while (offsetY > -BOARD_SIZE) {
            int toX = fromX + offsetX;
            int toY = fromY + offsetY;

            if (isMoveValid(board, fromX, fromY, toX, toY)) {
                char temp = board[fromY][fromX];
                board[fromY][fromX] = 0;
                board[toY][toX] = temp;

                score = getMinimax(board, depth + 1, !isMyTurn, !isWhiteTurn);

                if (isMyTurn) {
                    if (score > result) {
                        result = score;

                        this.currentMove.fromX = fromX;
                        this.currentMove.fromY = fromY;
                        this.currentMove.toX = toX;
                        this.currentMove.toY = toY;
                    }
                } else {
                    result = Math.min(score, result);
                }

                board[fromY][fromX] = temp;
                board[toY][toX] = 0;
            } else {
                break;
            }
            --offsetY;
        }

        return result;
    }

    private int getEvaluate(char[][] board) {
        int score = 0;

        for (int i = 0; i < BOARD_SIZE; ++i) {
            for (int j = 0; j < BOARD_SIZE; ++j) {
                if (board[i][j] == 0) {
                    continue;
                }

                char symbol = board[i][j];

                switch (symbol) {
                    case 'p':
                        score += 10;
                        break;
                    case 'P':
                        score -= 10;
                        break;
                    case 'n':
                    case 'b':
                        score += 30;
                        break;
                    case 'N':
                    case 'B':
                        score -= 30;
                        break;
                    case 'r':
                        score += 50;
                        break;
                    case 'R':
                        score -= 50;
                        break;
                    case 'q':
                        score += 90;
                        break;
                    case 'Q':
                        score -= 90;
                        break;
                    case 'k':
                        score += 900;
                        break;
                    case 'K':
                        score -= 900;
                        break;
                    default:
                        assert (false);
                        break;
                }
            }
        }

        if (!super.isWhite()) {
            score *= -1;
        }

        return score;
    }

    private boolean isMoveValid(char[][] board, int fromX, int fromY, int toX, int toY) {
        if (toX < 0 || toX >= BOARD_SIZE || toY < 0 || toY >= BOARD_SIZE) {
            return false;
        } else if (board[toY][toX] != 0 && Character.isLowerCase(board[fromY][fromX]) == Character.isLowerCase(board[toY][toX])) {
            return false;
        }

        return true;
    }

    private boolean isPawnMoveValid(char[][] board, int fromX, int fromY, int toX, int toY) {
        if (!isMoveValid(board, fromX, fromY, toX, toY)) {
            return false;
        } else if (fromX == toX && board[toY][toX] != 0) {
            return false;
        } else if (fromX != toX && board[toY][toX] == 0) {
            return false;
        } else if (Math.abs(fromY - toY) > 1) {
            int indexY = fromY < toY ? fromY + 1 : fromY - 1;

            return (board[indexY][fromX] == 0);
        }

        return true;
    }
}