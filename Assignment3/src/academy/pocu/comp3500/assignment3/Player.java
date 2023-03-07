package academy.pocu.comp3500.assignment3;

import academy.pocu.comp3500.assignment3.chess.Move;
import academy.pocu.comp3500.assignment3.chess.PlayerBase;

public class Player extends PlayerBase {
    private final static int BOARD_SIZE = 8;
    private final static int MAX_DEPTH = 4;
    private final static int PAWN_VALUE = 10;
    private final static int KNIGHT_VALUE = 30;
    private final static int BISHOP_VALUE = 40;
    private final static int ROOK_VALUE = 50;
    private final static int QUEEN_VALUE = 90;
    private final static int KING_VALUE = 900;

    private static final int[][] KNIGHT_MOVE_OFFSET = {
            {1, -2},
            {2, -1},
            {2, 1},
            {1, 2},
            {-1, 2},
            {-2, 1},
            {-2, -1},
            {-1, -2}
    };

    private static final int[][] KING_MOVE_OFFSET = {
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
        getMinimax(board, 0, true, super.isWhite());

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
        Move outMove = new Move();

        for (int i = 0; i < BOARD_SIZE; ++i) {
            for (int j = 0; j < BOARD_SIZE; ++j) {
                if (board[i][j] == 0) {
                    continue;
                } else if (isWhiteTurn && Character.isUpperCase(board[i][j])) {
                    continue;
                } else if (!isWhiteTurn && Character.isLowerCase(board[i][j])) {
                    continue;
                }

                char piece = board[i][j];
                int score;
                int toX;
                int toY;

                switch (piece) {
                    case 'p':
                        if (i == 6) {
                            toX = j;
                            toY = i - 2;

                            if (isPawnMoveValid(board, j, i, toX, toY)) {
                                char fromPiece = board[i][j];
                                char toPiece = board[toY][toX];

                                board[i][j] = 0;
                                board[toY][toX] = piece;

                                score = getMinimax(board, depth + 1, !isMyTurn, !isWhiteTurn);

                                if (isMyTurn) {
                                    if (score > result) {
                                        result = score;

                                        if (depth == 0) {
                                            this.currentMove.fromX = j;
                                            this.currentMove.fromY = i;
                                            this.currentMove.toX = toX;
                                            this.currentMove.toY = toY;
                                        }
                                    }
                                } else {
                                    result = Math.min(score, result);
                                }

                                board[i][j] = fromPiece;
                                board[toY][toX] = toPiece;
                            }
                        }

                        toX = j;
                        toY = i - 1;

                        if (isPawnMoveValid(board, j, i, toX, toY)) {
                            char fromPiece = board[i][j];
                            char toPiece = board[toY][toX];

                            board[i][j] = 0;
                            board[toY][toX] = piece;

                            score = getMinimax(board, depth + 1, !isMyTurn, !isWhiteTurn);

                            if (isMyTurn) {
                                if (score > result) {
                                    result = score;

                                    if (depth == 0) {
                                        this.currentMove.fromX = j;
                                        this.currentMove.fromY = i;
                                        this.currentMove.toX = toX;
                                        this.currentMove.toY = toY;
                                    }
                                }
                            } else {
                                result = Math.min(score, result);
                            }

                            board[i][j] = fromPiece;
                            board[toY][toX] = toPiece;
                        }

                        toX = j - 1;
                        toY = i - 1;

                        if (isPawnMoveValid(board, j, i, toX, toY)) {
                            char fromPiece = board[i][j];
                            char toPiece = board[toY][toX];

                            board[i][j] = 0;
                            board[toY][toX] = piece;

                            score = getMinimax(board, depth + 1, !isMyTurn, !isWhiteTurn);

                            if (isMyTurn) {
                                if (score > result) {
                                    result = score;

                                    if (depth == 0) {
                                        this.currentMove.fromX = j;
                                        this.currentMove.fromY = i;
                                        this.currentMove.toX = toX;
                                        this.currentMove.toY = toY;
                                    }
                                }
                            } else {
                                result = Math.min(score, result);
                            }

                            board[i][j] = fromPiece;
                            board[toY][toX] = toPiece;
                        }

                        toX = j + 1;
                        toY = i - 1;

                        if (isPawnMoveValid(board, j, i, toX, toY)) {
                            char fromPiece = board[i][j];
                            char toPiece = board[toY][toX];

                            board[i][j] = 0;
                            board[toY][toX] = piece;

                            score = getMinimax(board, depth + 1, !isMyTurn, !isWhiteTurn);

                            if (isMyTurn) {
                                if (score > result) {
                                    result = score;

                                    if (depth == 0) {
                                        this.currentMove.fromX = j;
                                        this.currentMove.fromY = i;
                                        this.currentMove.toX = toX;
                                        this.currentMove.toY = toY;
                                    }
                                }
                            } else {
                                result = Math.min(score, result);
                            }

                            board[i][j] = fromPiece;
                            board[toY][toX] = toPiece;
                        }
                        break;
                    case 'P':
                        toX = j;
                        toY = i + 2;

                        if (i == 1) {
                            if (isPawnMoveValid(board, j, i, toX, toY)) {
                                char fromPiece = board[i][j];
                                char toPiece = board[toY][toX];

                                board[i][j] = 0;
                                board[toY][toX] = piece;

                                score = getMinimax(board, depth + 1, !isMyTurn, !isWhiteTurn);

                                if (isMyTurn) {
                                    if (score > result) {
                                        result = score;

                                        if (depth == 0) {
                                            this.currentMove.fromX = j;
                                            this.currentMove.fromY = i;
                                            this.currentMove.toX = toX;
                                            this.currentMove.toY = toY;
                                        }
                                    }
                                } else {
                                    result = Math.min(score, result);
                                }

                                board[i][j] = fromPiece;
                                board[toY][toX] = toPiece;
                            }
                        }

                        toX = j;
                        toY = i + 1;

                        if (isPawnMoveValid(board, j, i, toX, toY)) {
                            char fromPiece = board[i][j];
                            char toPiece = board[toY][toX];

                            board[i][j] = 0;
                            board[toY][toX] = piece;

                            score = getMinimax(board, depth + 1, !isMyTurn, !isWhiteTurn);

                            if (isMyTurn) {
                                if (score > result) {
                                    result = score;

                                    if (depth == 0) {
                                        this.currentMove.fromX = j;
                                        this.currentMove.fromY = i;
                                        this.currentMove.toX = toX;
                                        this.currentMove.toY = toY;
                                    }
                                }
                            } else {
                                result = Math.min(score, result);
                            }

                            board[i][j] = fromPiece;
                            board[toY][toX] = toPiece;
                        }

                        toX = j - 1;
                        toY = i + 1;

                        if (isPawnMoveValid(board, j, i, toX, toY)) {
                            char fromPiece = board[i][j];
                            char toPiece = board[toY][toX];

                            board[i][j] = 0;
                            board[toY][toX] = piece;

                            score = getMinimax(board, depth + 1, !isMyTurn, !isWhiteTurn);

                            if (isMyTurn) {
                                if (score > result) {
                                    result = score;

                                    if (depth == 0) {
                                        this.currentMove.fromX = j;
                                        this.currentMove.fromY = i;
                                        this.currentMove.toX = toX;
                                        this.currentMove.toY = toY;
                                    }
                                }
                            } else {
                                result = Math.min(score, result);
                            }

                            board[i][j] = fromPiece;
                            board[toY][toX] = toPiece;
                        }

                        toX = j + 1;
                        toY = i + 1;

                        if (isPawnMoveValid(board, j, i, toX, toY)) {
                            char fromPiece = board[i][j];
                            char toPiece = board[toY][toX];

                            board[i][j] = 0;
                            board[toY][toX] = piece;

                            score = getMinimax(board, depth + 1, !isMyTurn, !isWhiteTurn);

                            if (isMyTurn) {
                                if (score > result) {
                                    result = score;

                                    if (depth == 0) {
                                        this.currentMove.fromX = j;
                                        this.currentMove.fromY = i;
                                        this.currentMove.toX = toX;
                                        this.currentMove.toY = toY;
                                    }
                                }
                            } else {
                                result = Math.min(score, result);
                            }

                            board[i][j] = fromPiece;
                            board[toY][toX] = toPiece;
                        }
                        break;
                    case 'n':
                    case 'N':
                        for (int k = 0; k < KNIGHT_MOVE_OFFSET.length; ++k) {
                            toX = j + KNIGHT_MOVE_OFFSET[k][0];
                            toY = i + KNIGHT_MOVE_OFFSET[k][1];

                            if (isMoveValid(board, j, i, toX, toY)) {
                                char fromPiece = board[i][j];
                                char toPiece = board[toY][toX];

                                board[i][j] = 0;
                                board[toY][toX] = piece;

                                score = getMinimax(board, depth + 1, !isMyTurn, !isWhiteTurn);

                                if (isMyTurn) {
                                    if (score > result) {
                                        result = score;

                                        if (depth == 0) {
                                            this.currentMove.fromX = j;
                                            this.currentMove.fromY = i;
                                            this.currentMove.toX = toX;
                                            this.currentMove.toY = toY;
                                        }
                                    }
                                } else {
                                    result = Math.min(score, result);
                                }

                                board[i][j] = fromPiece;
                                board[toY][toX] = toPiece;
                            }
                        }
                        break;
                    case 'b':
                    case 'B':
                        score = bishopMove(board, j, i, depth, isMyTurn, isWhiteTurn, outMove);

                        if (isMyTurn) {
                            if (score > result) {
                                result = score;

                                if (depth == 0) {
                                    this.currentMove.fromX = outMove.fromX;
                                    this.currentMove.fromY = outMove.fromY;
                                    this.currentMove.toX = outMove.toX;
                                    this.currentMove.toY = outMove.toY;
                                }
                            }
                        } else {
                            if (score < result) {
                                result = score;
                            }
                        }
                        break;
                    case 'r':
                    case 'R':
                        score = rookMove(board, j, i, depth, isMyTurn, isWhiteTurn, outMove);

                        if (isMyTurn) {
                            if (score > result) {
                                result = score;

                                if (depth == 0) {
                                    this.currentMove.fromX = outMove.fromX;
                                    this.currentMove.fromY = outMove.fromY;
                                    this.currentMove.toX = outMove.toX;
                                    this.currentMove.toY = outMove.toY;
                                }
                            }
                        } else {
                            result = Math.min(score, result);
                        }
                        break;
                    case 'q':
                    case 'Q':
                        score = bishopMove(board, j, i, depth, isMyTurn, isWhiteTurn, outMove);

                        if (isMyTurn) {
                            if (score > result) {
                                result = score;

                                if (depth == 0) {
                                    this.currentMove.fromX = outMove.fromX;
                                    this.currentMove.fromY = outMove.fromY;
                                    this.currentMove.toX = outMove.toX;
                                    this.currentMove.toY = outMove.toY;
                                }
                            }
                        } else {
                            result = Math.min(score, result);
                        }

                        score = rookMove(board, j, i, depth, isMyTurn, isWhiteTurn, outMove);

                        if (isMyTurn) {
                            if (score > result) {
                                result = score;

                                if (depth == 0) {
                                    this.currentMove.fromX = outMove.fromX;
                                    this.currentMove.fromY = outMove.fromY;
                                    this.currentMove.toX = outMove.toX;
                                    this.currentMove.toY = outMove.toY;
                                }
                            }
                        } else {
                            result = Math.min(score, result);
                        }
                        break;
                    case 'k':
                    case 'K':
                        for (int k = 0; k < KING_MOVE_OFFSET.length; ++k) {
                            toX = j + KING_MOVE_OFFSET[k][0];
                            toY = i + KING_MOVE_OFFSET[k][1];

                            if (isMoveValid(board, j, i, toX, toY)) {
                                char fromPiece = board[i][j];
                                char toPiece = board[toY][toX];

                                board[i][j] = 0;
                                board[toY][toX] = piece;

                                score = getMinimax(board, depth + 1, !isMyTurn, !isWhiteTurn);

                                if (isMyTurn) {
                                    if (score > result) {
                                        result = score;

                                        if (depth == 0) {
                                            this.currentMove.fromX = j;
                                            this.currentMove.fromY = i;
                                            this.currentMove.toX = toX;
                                            this.currentMove.toY = toY;
                                        }
                                    }
                                } else {
                                    result = Math.min(score, result);
                                }

                                board[i][j] = fromPiece;
                                board[toY][toX] = toPiece;
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

    private int bishopMove(char[][] board, int fromX, int fromY, final int depth, boolean isMyTurn, boolean isWhiteTurn, Move out) {
        int result;
        int score;
        int offsetX = 1;
        int offsetY = 1;

        result = isMyTurn ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        while (offsetX < BOARD_SIZE && offsetY < BOARD_SIZE) {
            int toX = fromX + offsetX;
            int toY = fromY + offsetY;

            if (isMoveValid(board, fromX, fromY, toX, toY)) {
                char fromPiece = board[fromY][fromX];
                char toPiece = board[toY][toX];

                board[fromY][fromX] = 0;
                board[toY][toX] = fromPiece;

                score = getMinimax(board, depth + 1, !isMyTurn, !isWhiteTurn);

                if (isMyTurn) {
                    if (score > result) {
                        result = score;

                        if (depth == 0) {
                            out.fromX = fromX;
                            out.fromY = fromY;
                            out.toX = toX;
                            out.toY = toY;
                        }
                    }
                } else {
                    result = Math.min(score, result);
                }

                board[fromY][fromX] = fromPiece;
                board[toY][toX] = toPiece;

                if (toPiece != 0) {
                    break;
                }
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
                char fromPiece = board[fromY][fromX];
                char toPiece = board[toY][toX];

                board[fromY][fromX] = 0;
                board[toY][toX] = fromPiece;

                score = getMinimax(board, depth + 1, !isMyTurn, !isWhiteTurn);

                if (isMyTurn) {
                    if (score > result) {
                        result = score;

                        if (depth == 0) {
                            out.fromX = fromX;
                            out.fromY = fromY;
                            out.toX = toX;
                            out.toY = toY;
                        }
                    }
                } else {
                    result = Math.min(score, result);
                }

                board[fromY][fromX] = fromPiece;
                board[toY][toX] = toPiece;

                if (toPiece != 0) {
                    break;
                }
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
                char fromPiece = board[fromY][fromX];
                char toPiece = board[toY][toX];

                board[fromY][fromX] = 0;
                board[toY][toX] = fromPiece;

                score = getMinimax(board, depth + 1, !isMyTurn, !isWhiteTurn);

                if (isMyTurn) {
                    if (score > result) {
                        result = score;

                        if (depth == 0) {
                            out.fromX = fromX;
                            out.fromY = fromY;
                            out.toX = toX;
                            out.toY = toY;
                        }
                    }
                } else {
                    result = Math.min(score, result);
                }

                board[fromY][fromX] = fromPiece;
                board[toY][toX] = toPiece;

                if (toPiece != 0) {
                    break;
                }
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
                char fromPiece = board[fromY][fromX];
                char toPiece = board[toY][toX];

                board[fromY][fromX] = 0;
                board[toY][toX] = fromPiece;

                score = getMinimax(board, depth + 1, !isMyTurn, !isWhiteTurn);

                if (isMyTurn) {
                    if (score > result) {
                        result = score;

                        if (depth == 0) {
                            out.fromX = fromX;
                            out.fromY = fromY;
                            out.toX = toX;
                            out.toY = toY;
                        }
                    }
                } else {
                    result = Math.min(score, result);
                }

                board[fromY][fromX] = fromPiece;
                board[toY][toX] = toPiece;

                if (toPiece != 0) {
                    break;
                }
            } else {
                break;
            }
            --offsetX;
            ++offsetY;
        }

        return result;
    }

    private int rookMove(char[][] board, int fromX, int fromY, final int depth, boolean isMyTurn, boolean isWhiteTurn, Move out) {
        int result;
        int score;
        int offsetX = 1;
        int offsetY = 0;

        result = isMyTurn ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        while (offsetX < BOARD_SIZE) {
            int toX = fromX + offsetX;
            int toY = fromY + offsetY;

            if (isMoveValid(board, fromX, fromY, toX, toY)) {
                char fromPiece = board[fromY][fromX];
                char toPiece = board[toY][toX];

                board[fromY][fromX] = 0;
                board[toY][toX] = fromPiece;

                score = getMinimax(board, depth + 1, !isMyTurn, !isWhiteTurn);

                if (isMyTurn) {
                    if (score > result) {
                        result = score;

                        if (depth == 0) {
                            out.fromX = fromX;
                            out.fromY = fromY;
                            out.toX = toX;
                            out.toY = toY;
                        }
                    }
                } else {
                    result = Math.min(score, result);
                }

                board[fromY][fromX] = fromPiece;
                board[toY][toX] = toPiece;

                if (toPiece != 0) {
                    break;
                }
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
                char fromPiece = board[fromY][fromX];
                char toPiece = board[toY][toX];

                board[fromY][fromX] = 0;
                board[toY][toX] = fromPiece;

                score = getMinimax(board, depth + 1, !isMyTurn, !isWhiteTurn);

                if (isMyTurn) {
                    if (score > result) {
                        result = score;

                        if (depth == 0) {
                            out.fromX = fromX;
                            out.fromY = fromY;
                            out.toX = toX;
                            out.toY = toY;
                        }
                    }
                } else {
                    result = Math.min(score, result);
                }

                board[fromY][fromX] = fromPiece;
                board[toY][toX] = toPiece;

                if (toPiece != 0) {
                    break;
                }
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
                char fromPiece = board[fromY][fromX];
                char toPiece = board[toY][toX];

                board[fromY][fromX] = 0;
                board[toY][toX] = fromPiece;

                score = getMinimax(board, depth + 1, !isMyTurn, !isWhiteTurn);

                if (isMyTurn) {
                    if (score > result) {
                        result = score;

                        if (depth == 0) {
                            out.fromX = fromX;
                            out.fromY = fromY;
                            out.toX = toX;
                            out.toY = toY;
                        }
                    }
                } else {
                    result = Math.min(score, result);
                }

                board[fromY][fromX] = fromPiece;
                board[toY][toX] = toPiece;

                if (toPiece != 0) {
                    break;
                }
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
                char fromPiece = board[fromY][fromX];
                char toPiece = board[toY][toX];

                board[fromY][fromX] = 0;
                board[toY][toX] = fromPiece;

                score = getMinimax(board, depth + 1, !isMyTurn, !isWhiteTurn);

                if (isMyTurn) {
                    if (score > result) {
                        result = score;

                        if (depth == 0) {
                            out.fromX = fromX;
                            out.fromY = fromY;
                            out.toX = toX;
                            out.toY = toY;
                        }
                    }
                } else {
                    result = Math.min(score, result);
                }

                board[fromY][fromX] = fromPiece;
                board[toY][toX] = toPiece;

                if (toPiece != 0) {
                    break;
                }
            } else {
                break;
            }
            --offsetY;
        }

        return result;
    }

    private int getEvaluate(char[][] board) {
        if (super.isWhite()) {
            return getEvaluateForWhite(board);
        }

        return getEvaluateForBlack(board);
    }

    private int getEvaluateForWhite(char[][] board) {
        int score = 0;

        for (int i = 0; i < BOARD_SIZE; ++i) {
            for (int j = 0; j < BOARD_SIZE; ++j) {
                if (board[i][j] == 0) {
                    continue;
                }

                char piece = board[i][j];
                int tempScore;

                switch (piece) {
                    case 'p':
                        tempScore = PAWN_VALUE;
                        break;
                    case 'P':
                        tempScore = -PAWN_VALUE;
                        break;
                    case 'n':
                        tempScore = KNIGHT_VALUE;
                        break;
                    case 'N':
                        tempScore = -KNIGHT_VALUE;
                        break;
                    case 'b':
                        tempScore = BISHOP_VALUE;
                        break;
                    case 'B':
                        tempScore = -BISHOP_VALUE;
                        break;
                    case 'r':
                        tempScore = ROOK_VALUE;
                        break;
                    case 'R':
                        tempScore = -ROOK_VALUE;
                        break;
                    case 'q':
                        tempScore = QUEEN_VALUE;
                        break;
                    case 'Q':
                        tempScore = -QUEEN_VALUE;
                        break;
                    case 'k':
                        tempScore = KING_VALUE;
                        break;
                    case 'K':
                        tempScore = -KING_VALUE;
                        break;
                    default:
                        assert (false);
                        return -1;
                }

                score += tempScore;
            }
        }

        return score;
    }

    private int getEvaluateForBlack(char[][] board) {
        int score = 0;

        for (int i = 0; i < BOARD_SIZE; ++i) {
            for (int j = 0; j < BOARD_SIZE; ++j) {
                if (board[i][j] == 0) {
                    continue;
                }

                char piece = board[i][j];
                int tempScore;

                switch (piece) {
                    case 'p':
                        tempScore = -PAWN_VALUE;
                        break;
                    case 'P':
                        tempScore = PAWN_VALUE;
                        break;
                    case 'n':
                        tempScore = -KNIGHT_VALUE;
                        break;
                    case 'N':
                        tempScore = KNIGHT_VALUE;
                        break;
                    case 'b':
                        tempScore = -BISHOP_VALUE;
                        break;
                    case 'B':
                        tempScore = BISHOP_VALUE;
                        break;
                    case 'r':
                        tempScore = -ROOK_VALUE;
                        break;
                    case 'R':
                        tempScore = ROOK_VALUE;
                        break;
                    case 'q':
                        tempScore = -QUEEN_VALUE;
                        break;
                    case 'Q':
                        tempScore = QUEEN_VALUE;
                        break;
                    case 'k':
                        tempScore = -KING_VALUE;
                        break;
                    case 'K':
                        tempScore = KING_VALUE;
                        break;
                    default:
                        assert (false);
                        return -1;
                }

                if (i == 3 && j == 3 || i == 4 && j == 3
                        || i == 3 && j == 4 || i == 4 && j == 4) {
                    tempScore *= 2;
                }

                score += tempScore;
            }
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