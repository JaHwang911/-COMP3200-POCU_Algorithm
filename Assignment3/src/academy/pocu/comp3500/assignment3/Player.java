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

    private int getMinimax(final char[][] board, final int depth, final boolean isMyTurn, final boolean isWhiteTurn) {
        if (depth == MAX_DEPTH) {
            return getEvaluate(board);
        }

        int result = isMyTurn ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        Move outMove = new Move();
        int bitMask = 1 << 5;

        for (int y = 0; y < BOARD_SIZE; ++y) {
            for (int x = 0; x < BOARD_SIZE; ++x) {
                if (board[y][x] == 0) {
                    continue;
                } else if (isWhiteTurn && (board[y][x] & bitMask) == 0) {
                    continue;
                } else if (!isWhiteTurn && (board[y][x] & bitMask) != 0) {
                    continue;
                }

                char piece = board[y][x];
                int score;
                int toX;
                int toY;

                switch (piece) {
                    case 'p':
                        toX = x;
                        toY = y - 2;

                        if (isPawnMoveValid(board, x, y, toX, toY)) {
                            char fromPiece = board[y][x];
                            char toPiece = board[toY][toX];

                            board[y][x] = 0;
                            board[toY][toX] = piece;

                            score = getMinimax(board, depth + 1, !isMyTurn, !isWhiteTurn);

                            if (isMyTurn) {
                                if (score > result) {
                                    result = score;

                                    if (depth == 0) {
                                        this.currentMove.fromX = x;
                                        this.currentMove.fromY = y;
                                        this.currentMove.toX = toX;
                                        this.currentMove.toY = toY;
                                    }
                                }
                            } else {
                                result = Math.min(score, result);
                            }

                            board[y][x] = fromPiece;
                            board[toY][toX] = toPiece;
                        }

                        toX = x;
                        toY = y - 1;

                        if (isPawnMoveValid(board, x, y, toX, toY)) {
                            char fromPiece = board[y][x];
                            char toPiece = board[toY][toX];

                            board[y][x] = 0;
                            board[toY][toX] = piece;

                            score = getMinimax(board, depth + 1, !isMyTurn, !isWhiteTurn);

                            if (isMyTurn) {
                                if (score > result) {
                                    result = score;

                                    if (depth == 0) {
                                        this.currentMove.fromX = x;
                                        this.currentMove.fromY = y;
                                        this.currentMove.toX = toX;
                                        this.currentMove.toY = toY;
                                    }
                                }
                            } else {
                                result = Math.min(score, result);
                            }

                            board[y][x] = fromPiece;
                            board[toY][toX] = toPiece;
                        }

                        toX = x - 1;
                        toY = y - 1;

                        if (isPawnMoveValid(board, x, y, toX, toY)) {
                            char fromPiece = board[y][x];
                            char toPiece = board[toY][toX];

                            board[y][x] = 0;
                            board[toY][toX] = piece;

                            score = getMinimax(board, depth + 1, !isMyTurn, !isWhiteTurn);

                            if (isMyTurn) {
                                if (score > result) {
                                    result = score;

                                    if (depth == 0) {
                                        this.currentMove.fromX = x;
                                        this.currentMove.fromY = y;
                                        this.currentMove.toX = toX;
                                        this.currentMove.toY = toY;
                                    }
                                }
                            } else {
                                result = Math.min(score, result);
                            }

                            board[y][x] = fromPiece;
                            board[toY][toX] = toPiece;
                        }

                        toX = x + 1;
                        toY = y - 1;

                        if (isPawnMoveValid(board, x, y, toX, toY)) {
                            char fromPiece = board[y][x];
                            char toPiece = board[toY][toX];

                            board[y][x] = 0;
                            board[toY][toX] = piece;

                            score = getMinimax(board, depth + 1, !isMyTurn, !isWhiteTurn);

                            if (isMyTurn) {
                                if (score > result) {
                                    result = score;

                                    if (depth == 0) {
                                        this.currentMove.fromX = x;
                                        this.currentMove.fromY = y;
                                        this.currentMove.toX = toX;
                                        this.currentMove.toY = toY;
                                    }
                                }
                            } else {
                                result = Math.min(score, result);
                            }

                            board[y][x] = fromPiece;
                            board[toY][toX] = toPiece;
                        }
                        break;
                    case 'P':
                        toX = x;
                        toY = y + 2;

                        if (isPawnMoveValid(board, x, y, toX, toY)) {
                            char fromPiece = board[y][x];
                            char toPiece = board[toY][toX];

                            board[y][x] = 0;
                            board[toY][toX] = piece;

                            score = getMinimax(board, depth + 1, !isMyTurn, !isWhiteTurn);

                            if (isMyTurn) {
                                if (score > result) {
                                    result = score;

                                    if (depth == 0) {
                                        this.currentMove.fromX = x;
                                        this.currentMove.fromY = y;
                                        this.currentMove.toX = toX;
                                        this.currentMove.toY = toY;
                                    }
                                }
                            } else {
                                result = Math.min(score, result);
                            }

                            board[y][x] = fromPiece;
                            board[toY][toX] = toPiece;
                        }

                        toX = x;
                        toY = y + 1;

                        if (isPawnMoveValid(board, x, y, toX, toY)) {
                            char fromPiece = board[y][x];
                            char toPiece = board[toY][toX];

                            board[y][x] = 0;
                            board[toY][toX] = piece;

                            score = getMinimax(board, depth + 1, !isMyTurn, !isWhiteTurn);

                            if (isMyTurn) {
                                if (score > result) {
                                    result = score;

                                    if (depth == 0) {
                                        this.currentMove.fromX = x;
                                        this.currentMove.fromY = y;
                                        this.currentMove.toX = toX;
                                        this.currentMove.toY = toY;
                                    }
                                }
                            } else {
                                result = Math.min(score, result);
                            }

                            board[y][x] = fromPiece;
                            board[toY][toX] = toPiece;
                        }

                        toX = x - 1;
                        toY = y + 1;

                        if (isPawnMoveValid(board, x, y, toX, toY)) {
                            char fromPiece = board[y][x];
                            char toPiece = board[toY][toX];

                            board[y][x] = 0;
                            board[toY][toX] = piece;

                            score = getMinimax(board, depth + 1, !isMyTurn, !isWhiteTurn);

                            if (isMyTurn) {
                                if (score > result) {
                                    result = score;

                                    if (depth == 0) {
                                        this.currentMove.fromX = x;
                                        this.currentMove.fromY = y;
                                        this.currentMove.toX = toX;
                                        this.currentMove.toY = toY;
                                    }
                                }
                            } else {
                                result = Math.min(score, result);
                            }

                            board[y][x] = fromPiece;
                            board[toY][toX] = toPiece;
                        }

                        toX = x + 1;
                        toY = y + 1;

                        if (isPawnMoveValid(board, x, y, toX, toY)) {
                            char fromPiece = board[y][x];
                            char toPiece = board[toY][toX];

                            board[y][x] = 0;
                            board[toY][toX] = piece;

                            score = getMinimax(board, depth + 1, !isMyTurn, !isWhiteTurn);

                            if (isMyTurn) {
                                if (score > result) {
                                    result = score;

                                    if (depth == 0) {
                                        this.currentMove.fromX = x;
                                        this.currentMove.fromY = y;
                                        this.currentMove.toX = toX;
                                        this.currentMove.toY = toY;
                                    }
                                }
                            } else {
                                result = Math.min(score, result);
                            }

                            board[y][x] = fromPiece;
                            board[toY][toX] = toPiece;
                        }
                        break;
                    case 'n':
                    case 'N':
                        for (int k = 0; k < KNIGHT_MOVE_OFFSET.length; ++k) {
                            toX = x + KNIGHT_MOVE_OFFSET[k][0];
                            toY = y + KNIGHT_MOVE_OFFSET[k][1];

                            if (isMoveValid(board, x, y, toX, toY)) {
                                char fromPiece = board[y][x];
                                char toPiece = board[toY][toX];

                                board[y][x] = 0;
                                board[toY][toX] = piece;

                                score = getMinimax(board, depth + 1, !isMyTurn, !isWhiteTurn);

                                if (isMyTurn) {
                                    if (score > result) {
                                        result = score;

                                        if (depth == 0) {
                                            this.currentMove.fromX = x;
                                            this.currentMove.fromY = y;
                                            this.currentMove.toX = toX;
                                            this.currentMove.toY = toY;
                                        }
                                    }
                                } else {
                                    result = Math.min(score, result);
                                }

                                board[y][x] = fromPiece;
                                board[toY][toX] = toPiece;
                            }
                        }
                        break;
                    case 'b':
                    case 'B':
                        score = bishopMove(board, x, y, depth, isMyTurn, isWhiteTurn, outMove);

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
                        score = rookMove(board, x, y, depth, isMyTurn, isWhiteTurn, outMove);

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
                        score = bishopMove(board, x, y, depth, isMyTurn, isWhiteTurn, outMove);

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

                        score = rookMove(board, x, y, depth, isMyTurn, isWhiteTurn, outMove);

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
                            toX = x + KING_MOVE_OFFSET[k][0];
                            toY = y + KING_MOVE_OFFSET[k][1];

                            if (isMoveValid(board, x, y, toX, toY)) {
                                char fromPiece = board[y][x];
                                char toPiece = board[toY][toX];

                                board[y][x] = 0;
                                board[toY][toX] = piece;

                                score = getMinimax(board, depth + 1, !isMyTurn, !isWhiteTurn);

                                if (isMyTurn) {
                                    if (score > result) {
                                        result = score;

                                        if (depth == 0) {
                                            this.currentMove.fromX = x;
                                            this.currentMove.fromY = y;
                                            this.currentMove.toX = toX;
                                            this.currentMove.toY = toY;
                                        }
                                    }
                                } else {
                                    result = Math.min(score, result);
                                }

                                board[y][x] = fromPiece;
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

    private int bishopMove(char[][] board, final int fromX, final int fromY, final int depth, boolean isMyTurn, boolean isWhiteTurn, final Move out) {
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
        int score;
        int whiteScore = 0;
        int blackScore = 0;

        final StringBuilder sb = new StringBuilder(128);

        sb.append("  ");
        for (int x = 0; x < BOARD_SIZE; ++x) {
            sb.append((char) (x + 'a'));
        }

        sb.append(System.lineSeparator());

        addHorizontalBorder(sb);

        for (int y = 0; y < BOARD_SIZE; ++y) {
            for (int x = 0; x < BOARD_SIZE; ++x) {
                if (board[y][x] == 0) {
                    continue;
                }

                char piece = board[y][x];

                switch (piece) {
                    case 'p':
                        whiteScore += PAWN_VALUE;
                        break;
                    case 'P':
                        blackScore += PAWN_VALUE;
                        break;
                    case 'n':
                        whiteScore += KNIGHT_VALUE;
                        break;
                    case 'N':
                        blackScore += KNIGHT_VALUE;
                        break;
                    case 'b':
                        whiteScore += BISHOP_VALUE;
                        break;
                    case 'B':
                        blackScore += BISHOP_VALUE;
                        break;
                    case 'r':
                        whiteScore += ROOK_VALUE;
                        break;
                    case 'R':
                        blackScore += ROOK_VALUE;
                        break;
                    case 'q':
                        whiteScore += QUEEN_VALUE;
                        break;
                    case 'Q':
                        blackScore += QUEEN_VALUE;
                        break;
                    case 'k':
                        whiteScore += KING_VALUE;
                        break;
                    case 'K':
                        blackScore += KING_VALUE;
                        break;
                    default:
                        assert (false);
                        return -1;
                }
            }
        }

        if (super.isWhite()) {
            score = whiteScore - blackScore;
        } else {
            score = blackScore - whiteScore;
        }

        return score;
    }

    private static void addHorizontalBorder(StringBuilder sb) {
        sb.append(' ');
        sb.append('+');
        for (int y = 0; y < BOARD_SIZE; ++y) {
            sb.append('-');
        }
        sb.append('+');
        sb.append(System.lineSeparator());
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
            if (fromY != 1 && fromY != 6) {
                return false;
            }

            int indexY = fromY < toY ? fromY + 1 : fromY - 1;

            return (board[indexY][fromX] == 0);
        }

        return true;
    }
}